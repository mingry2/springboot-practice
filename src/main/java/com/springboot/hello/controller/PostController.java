package com.springboot.hello.controller;


import com.springboot.hello.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // spring이 controller로 등록함
@RequestMapping("/api/v1/post-api") // --내부에 선언되는 메서드에서 사용할 공통 URL 설정
public class PostController {
    /*
     POST로 요청하기 -> post 형식의 요청은 클라이언트가 서버에 리소스를 저장하는데에 사용
     그러므로 클라이언트의 요청 트래픽에 '값' 포함돼 있음
     해당 리소스를 담기위해 HTTP Body에 값을 넣어 전송함
     */

    // 아래의 예제는 별도의 리소스를 받지 않고 단지 POST 요청만 받는 메서드를 구현한것
    // http://localhost:8080/api/v1/post-api/domain
    @RequestMapping(value = "/domain", method = RequestMethod.POST)
    public String postExample() {
        return "Hello Post API";
    }

    //
    // http://localhost:8080/api/v1/post-api/member
    @PostMapping(value ="/member")
    public String postMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    /*
    Body 영역에 작성되는 값은 일정한 형태를 취함
    -> 일반적으로 JSON(JavaScript Object Notation)형식으로 전송
        {
      "name":"minkyoung",
      "email":"minkyoung@gmail.com",
      "organization":"likelion",
      "age":29,
      "isMarried":"false"
        }
     */
    // http://localhost:8080/api/v1/post-api/member2
    @PostMapping("/member2")
    public String postMember(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }
}
