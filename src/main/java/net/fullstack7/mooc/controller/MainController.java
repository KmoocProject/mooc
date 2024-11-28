package net.fullstack7.mooc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    @GetMapping("/main")
    public String main() {
        return "main/main";
    }

    @GetMapping("/footer/personalrule")
    public String personalrule() {
        return "main/footer/personalrule";
    }

    @GetMapping("/footer/userterms")
    public String userterms() {
        return "main/footer/userterms";
    }

    @GetMapping("/footer/copyright")
    public String copyright() {
        return "main/footer/copyright";
    }

    @GetMapping("/footer/intro")
    public String intro() {
        return "main/footer/intro";
    }

    @GetMapping("/footer/business")
    public String business() {
        return "main/footer/business";
    }


}
