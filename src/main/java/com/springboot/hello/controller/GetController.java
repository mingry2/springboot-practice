package com.springboot.hello.controller;

import com.springboot.hello.domain.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*
컨트롤러에 포함된 로직에서는
애플리케이션의 사용자 또는 클라이언트가 입력한 값에 대한 응답을 수행함
특별한 경우를 제외한 모든 요청은 컨트롤러를 통해 진행함
*/

@RestController // spring이 controller로 등록함
@RequestMapping("/api/v1/get-api") // --내부에 선언되는 메서드에서 사용할 공통 URL 설정
@Slf4j
public class GetController {

    // @RequestMapping -> controller가 할당 된 후 어떤 method를 실행할지 연결해주는 어노테이션
//    @RequestMapping("/hello") // --post, get 둘 다 사용가능
//    public String hello(){
//        return "Hello World";
//    }

    // http://localhost:8080/api/v1/get-api/hello
    // GET 만 사용하기(특정 요청 형식 받기 method = RequesMethod.[])
    @RequestMapping(value = "/hello", method = RequestMethod.GET) // --post, get 둘 다 사용가능
    public String hello(){
        log.info("hello로 요청이 들어왔습니다.");
        return "Hello World";
    }

    // http://localhost:8080/api/v1/get-api/name
    // 버전 4.3 이후로는 @GetMapping 등 여러 어노테이션이 나왔음 @RequestMapping 사용안함
    @GetMapping(value = "/name")
    public String getName() {
        log.info("getName으로 요청이 들어왔습니다.");
        return "minkyoung";
    }

    // http://localhost:8080/api/v1/get-api/variable1/{variable}
    @GetMapping(value = "/variable1/{variable}") // -> 어느위치에서 값을 받을지 {} 지정해준다.
    public String getVariable1(@PathVariable String variable) {
        // @PathVariable [타입] {가져올값} 이렇게 치면 그 값이 리턴됨
        // 주소를 통해 variable를 간단히 넘기고 싶을 때 사용
        // @GetMapping 과 @PathVariable에 설정한 변수명은 동일하게 해줘야함
        log.info("getVariable1으로 요청이 들어왔습니다. variable:{}", variable);
        return variable;
    }

    // http://localhost:8080/api/v1/get-api/variable1/{variable}
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        // @PathVariable [타입] {가져올값} 이렇게 치면 그 값이 리턴됨
        // 주소를 통해 variable를 넘기고 싶을 때 사용
        // @GetMapping 과 @PathVariable에 설정한 변수명은 동일하게 해줘야함
        return var;
    }

    // http://localhost:8080/api/v1/get-api/request1?name=value1&email=value2&organization=value3
    @GetMapping(value = "/request1")
    // GET요청 구현 시 url을 경로에 담아 요청 보내는 방법도 있지만 쿼리형식으로 값을 전달 할 수 있음
    // -> @RequestParam을 통해 전달하고자하는 변수에 값을 담아 보냄
    // /request1 옆에 '?'으로 쿼리형식과 연결함 처음에만 '?'이고 이후 연결은 '&'으로 진행
    public String getRequestParam1(@RequestParam String name,
                                   @RequestParam String email,
                                   @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }

    // http://localhost:8080/api/v1/get-api/request2?key1=value1&key2=value2
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        param.entrySet().forEach((map)->{
            System.out.printf("key:%s value:%s\n", map.getKey(), map.getValue());
        });
        return "request2가 호출 완료 되었습니다.";
    }

    // DTO를 활용하여 GET 메서드 구현
    // DTO(Data Transfer Object) 다른 레이어 간의 데이터 교환에 활용
    // -> 각 클래스 및 인터페이스를 호출하면서 전달하는 매개변수로 사용되는 데이터 객체
    // http://localhost:8080/api/v1/get-api/request3?key1=value1&key2=value2
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDto) {

        return memberDto.toString();
    }
}

