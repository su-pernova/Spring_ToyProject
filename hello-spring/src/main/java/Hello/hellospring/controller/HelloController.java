package Hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // GetMapping = Getmethod 의미
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    // ResponseBody = HTTP 통신 프로토콜의 Body 부분에 데이터를 return 값으로 직접 넣어주겠다는 의미
    // View 가 존재하지 않고, return 값이 그대로 내려간다.
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 처음으로 문자가 아닌 '객체'를 넘긴다
    }

    static class Hello{
        private  String name;

        public String getName(){
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
