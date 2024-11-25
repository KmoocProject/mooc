package net.fullstack7.mooc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dummy")
public class DummyController {

  @GetMapping("/regist-lecture")
  public String registLecture(Model model) {
    model.addAttribute("currentPage", "regist-lecture");
    return "teacher/registLecture";
  }

  @GetMapping("/my-lectures")
  public String myLectures(Model model) {
    model.addAttribute("currentPage", "my-lectures");
    return "teacher/myLectures";
  }
}
