package net.fullstack7.mooc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/learn")
public class LearnController {
    @GetMapping("/view/{courseId}")
    public String learnview(@PathVariable int courseId) {
        
        return "learn/view";
    }
}
