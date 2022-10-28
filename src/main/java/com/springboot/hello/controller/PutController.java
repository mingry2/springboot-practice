package com.springboot.hello.controller;


import com.springboot.hello.domain.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // spring이 controller로 등록함
@RequestMapping("/api/v1/put-api") // --내부에 선언되는 메서드에서 사용할 공통 URL 설정
public class PutController {

    @PostMapping("/member3")
    public ResponseEntity<MemberDto> postMemberDto3(@RequestBody MemberDto memberDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}
