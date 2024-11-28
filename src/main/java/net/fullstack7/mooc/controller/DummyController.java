package net.fullstack7.mooc.controller;

import net.fullstack7.mooc.service.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dummy")
public class DummyController {
  private final SubjectService subjectService;

  @GetMapping("/regist-lecture")
  public String registLecture(Model model) {
    model.addAttribute("currentPage", "regist-lecture");
    model.addAttribute("subjects", subjectService.getAllSubjects());
    return "teacher/registLecture";
  }

  @GetMapping("/my-lectures")
  public String myLectures(Model model) {
    model.addAttribute("currentPage", "my-lectures");
    return "teacher/myLectures";
  }

  @GetMapping("/learn/quizview")
  public String noticeList() {
    return "learn/quizview";
  }
  @GetMapping("/admin/memberList")
  public String memberList() {
    return "admin/memberList";
  }
}