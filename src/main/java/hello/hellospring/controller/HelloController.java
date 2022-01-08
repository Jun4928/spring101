package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        String a = "spring is cool, java is not bad!".toUpperCase(Locale.ROOT);
        model.addAttribute("data", a);
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name= "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // body 에 내용을 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // body 에 내용을 직접 반환한다.
    // viewResolver 대신에 HttpMessageConverter 가 동작한다.
    // MappingJackson2HttpMessageConverter 가 기본 객체처리다.
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체가 리턴되면 default로 JsonConverter 가 작동된다.
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
