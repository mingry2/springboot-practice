package com.springboot.hello.controller;


import com.springboot.hello.dao.UserDao;
import com.springboot.hello.domain.User;
import com.springboot.hello.domain.dto.UserRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserDaoController {

    private final UserDao userDao;

    public UserDaoController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("")
    public ResponseEntity<Integer> add(@RequestBody UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getId(), userRequestDto.getName(), userRequestDto.getPassword());
        return ResponseEntity
                .ok()
                .body(userDao.add(user));
    }

    @DeleteMapping("")
    public ResponseEntity<Integer> deleteAll(){
        return ResponseEntity
                .ok()
                .body(userDao.deleteAll());

    }
}
