package Hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // localhost:8080으로 들어오면 아래 부분이 호출된다.
    @GetMapping("/")
    public String home(){
        // home.html이 호출된다.
        return "home";
    }
}
