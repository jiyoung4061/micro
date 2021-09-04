package com.test.springboot.study.web;

import com.test.springboot.study.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
    http://localhost:8080/hello (GET방식)

    Method
        GET, POST, PUT, DELETE, OPTIONS

    @RestController
        JSON(name : hong) 으로 변환하는 컨트롤러
        Spring에서의 @ResponseBody 에 해당
    @GetMapping / @PostMapping / @PutMapping /@DeleteMapping
        select      insert          update      delete

    HTTP Error Code(Status Code)
    Google : RFC HTTP , 404..
        1xx : Trying
        2xx : OK
        3xx : Temporary Error, Redirection Error
        4xx : Permenant Error, Client Error
        5xx : Server Error
        6xx : Global Error

*/

/*
    1-7 롬복(Lombock)
        Getter, Setter, Default Constructor, toString등을 Annotation으로 자동 생성
        롬복 설치(Ctrl + Shift + A)
            plugins > Lombock 필요한 경우 설치
            File > Settings > Build, Excuting, Deployment > Compiler > Annotation Processors
            화면의 오른쪽에 Enable annotation processing 체크 추가
 */

// http://localhost:8080/hello/dto?name=홍길동&age=40

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("age") int age) {
        return new HelloResponseDto(name, age);
    }
}
