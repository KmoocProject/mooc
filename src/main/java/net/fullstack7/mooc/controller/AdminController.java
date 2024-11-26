package net.fullstack7.mooc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("main")
    public String main() {
        return "admin/main";
    }

    @GetMapping("login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("member")
    public String member() {
        return "admin/member";
    }

    @GetMapping("course/courselist")
    public String courselist() {
        return "admin/course/courselist";
    }

    @GetMapping("course/courseview")
    public String courseview() {
        return "admin/course/courseview";
    }
}



