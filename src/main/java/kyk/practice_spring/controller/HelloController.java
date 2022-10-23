package kyk.practice_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // 컨트롤러 어노테이션
public class HelloController {

    @GetMapping("hello") // MVC 방식 - 웹 어플리케이션에서 /hello이면 메서드가 호출되는 방식
    public String hello(Model model){
        model.addAttribute("data","hello!!"); // 전달 모델명 data로 선언, 전달 값 hello!!로 선언
        return "hello";
    }

    @GetMapping("hello-mvc") // MVC 방식 - 외부 param에서 가져오는 방식
    public String helloMvc(@RequestParam("name") String name, Model model){  // 외부에서 param을 받아옴
        model.addAttribute("name", name); // 외부에서 들어온 파라미터를 name으로 받아옴
        return "hello-template";
    }

    @GetMapping("hello-string") // API 방식
    @ResponseBody // html body부에 아래 데이터를 그대로 넣어준다는 뜻
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // 호출된 페이지 소스를 보면 html태그가 없고 "hello spring" 해당 문자만 그대로 나옴, 즉 이렇게 사용 잘 안함
    }

    @GetMapping("hello-api") // 보통 우리가 말하는 API 방식
    @ResponseBody // 기본으로 JSON으로 반환하는 방식
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;

        // 게터세터 = 자바 빈 규약  위 private name은 바로 못꺼내오는데 라이브러리 등 외부에서 사용해야할 때 이렇게 게터세터로 접근해서 사용
        //        = 프로퍼티 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
