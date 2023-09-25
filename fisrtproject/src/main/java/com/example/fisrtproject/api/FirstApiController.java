package com.example.fisrtproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //1. RestAPI용 컨트롤러 ! JSON을 반환 !
public class FirstApiController {

//    2. localhost::8080/api/hello를 들어가면 화면에 "hello"를 띄우게 할거임
    @GetMapping("/api/hello") //그러기 위해서 Getmapping으로 url 연결
    public String hello() {
        return "hello world"; //3. 서버를 확인해보면 화면에 hello world 가 보일거임
    }

//    일반 컨트롤러와 레스트 컨토를러와의 차이? : 반환 타입이 다르다.
//    일반 컨트롤러 : 반환을 뷰 템플릿 페이지로
//    레스트 컨트롤러 : 반환을 일반적으로 JOSN or text로

}
