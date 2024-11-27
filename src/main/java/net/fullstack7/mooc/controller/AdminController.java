package net.fullstack7.mooc.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.domain.Admin;
import net.fullstack7.mooc.domain.Member;
import net.fullstack7.mooc.domain.Notice;
import net.fullstack7.mooc.domain.Teacher;
import net.fullstack7.mooc.dto.AdminLoginDTO;
import net.fullstack7.mooc.dto.AdminSearchDTO;
import net.fullstack7.mooc.dto.NoticeDTO;
import net.fullstack7.mooc.dto.PageDTO;
import net.fullstack7.mooc.service.AdminServiceIf;
import net.fullstack7.mooc.service.NoticeServiceIf;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceIf adminService;
    private final NoticeServiceIf noticeService;

    @GetMapping("/main")
    public String main(Model model) {
        return "admin/main";
    }

    @GetMapping("/login")
    public String loginGet(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("loginAdminId") != null) {
            redirectAttributes.addFlashAttribute("errors", "이미 로그인했습니다.");
            return "redirect:/admin/main";
        }
        return "admin/login";
    }

    @PostMapping("/login")
    public String loginPost(@Valid AdminLoginDTO adminLoginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes
            , HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/admin/login";
        }
        String result = adminService.login(adminLoginDTO);
        if (result == null) {
            redirectAttributes.addFlashAttribute("errors", "비밀번호가 일치하지 않거나 존재하지 않는 회원입니다.");
            return "redirect:/admin/login";
        }
        session.setAttribute("loginAdminId", result);
        return "redirect:/admin/main";
    }

    @GetMapping("/logout")
    public String logoutGet(HttpSession session) {
        session.removeAttribute("loginAdminId");
        return "redirect:/admin/login";
    }


    @GetMapping("/memberList")
    public String memberListGet(Model model, @Valid AdminSearchDTO adminSearchDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            adminSearchDTO = AdminSearchDTO.<Teacher>builder().build();
        }

        if (adminSearchDTO.getTypeSelect().equals("t"))
            model.addAttribute("pageinfo", adminService.getTeachers(adminSearchDTO));
        else
            model.addAttribute("pageinfo", adminService.getMembers(adminSearchDTO));

        model.addAttribute("searchinfo", adminSearchDTO);

        return "admin/memberList";
    }

    @GetMapping("/memberView")
    public String memberViewGet(@RequestParam(defaultValue = "0") String memberId, @RequestParam(defaultValue = "t") String typeSelect
            , Model model, RedirectAttributes redirectAttributes) {

        if (memberId.equals("0")) {
            redirectAttributes.addFlashAttribute("errors", "조회할 계정을 선택하세요.");
            return "redirect:/admin/memberList";
        }

        if (typeSelect.equals("t")) {
            Teacher item = adminService.getTeacher(memberId);
            if (item == null) {
                redirectAttributes.addFlashAttribute("존재하지 않는 회원입니다.");
                return "redirect:/admin/memberList";
            }
            model.addAttribute("item", item);
        } else if (typeSelect.equals("s")) {
            Member item = adminService.getMember(memberId);
            if (item == null) {
                redirectAttributes.addFlashAttribute("존재하지 않는 회원입니다.");
                return "redirect:/admin/memberList";
            }
            model.addAttribute("item", item);
        } else {
            redirectAttributes.addFlashAttribute("errors", "조회 실패 - 존재하지 않는 회원 유형");
            return "redirect:/admin/memberList";
        }

        return "admin/memberView";
    }

    @GetMapping("/memberModify")
    public String memberModifyGet(@RequestParam(defaultValue = "0") String memberId, Model model, RedirectAttributes redirectAttributes) {

        if (memberId.equals("0")) {
            redirectAttributes.addFlashAttribute("errors", "수정할 계정을 선택하세요.");
            return "redirect:/admin/memberList";
        }


        Member item = adminService.getMember(memberId);
        if (item == null) {
            redirectAttributes.addFlashAttribute("존재하지 않는 회원입니다.");
            model.addAttribute("item", item);
        } else {
            redirectAttributes.addFlashAttribute("errors", "조회 실패 - 존재하지 않는 회원 유형");
            return "redirect:/admin/memberList";
        }

        return "admin/memberModify";
    }

    @PostMapping("/memberModify")
    public String memberModifyPost(@Valid Member member, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

        }

        return "redirect:/admin/memberList";
    }

    @GetMapping("/teacherModify")
    public String teacherModifyGet(@RequestParam(defaultValue = "0") String memberId, Model model, RedirectAttributes redirectAttributes) {
        if (memberId.equals("0")) {
            redirectAttributes.addFlashAttribute("errors", "수정할 계정을 선택하세요.");
            return "redirect:/admin/memberList";
        }
        Teacher item = adminService.getTeacher(memberId);
        if (item == null) {
            redirectAttributes.addFlashAttribute("존재하지 않는 회원입니다.");
            model.addAttribute("item", item);
        } else {
            redirectAttributes.addFlashAttribute("errors", "조회 실패 - 존재하지 않는 회원 유형");
            return "redirect:/admin/memberList";
        }

        return "admin/teacherModify";
    }

    @GetMapping("/memberDelete")
    public String memberDeleteGet(@RequestParam(defaultValue = "0") String memberId, @RequestParam(defaultValue = "") String typeSelect, Model model, RedirectAttributes redirectAttributes) {
        if (memberId.equals("0")) {
            redirectAttributes.addFlashAttribute("errors", "삭제할 회원을 선택해주세요.");
            return "redirect:/admin/memberList";
        }
        if ((!typeSelect.equals("t") && !typeSelect.equals("s"))) {
            redirectAttributes.addFlashAttribute("errors", "회원 분류 설정 오류");
            return "redirect:/admin/memberList";
        }


        redirectAttributes.addFlashAttribute("errors", adminService.deleteMember(memberId, typeSelect));
        return "redirect:/admin/memberList";
    }

    @GetMapping("/approve/{teacherId}")
    public String approveGet(@PathVariable String teacherId, Model model, RedirectAttributes redirectAttributes) {
        if (teacherId == null) {
        }

        return "redirect:/admin/memberList";
    }


    /*
    나중에 김경민이 다 하면 날먹하기
     */
    @GetMapping("/courseList")
    public String courseListGet(Model model, RedirectAttributes redirectAttributes) {
        return "admin/course/courselist";
    }

    @GetMapping("/noticeList")
    public String noticeListGet(@Valid PageDTO<Notice> pageDTO, BindingResult bindingResult
            , Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            pageDTO = PageDTO.<Notice>builder().build();
        }

        pageDTO.initialize();

        Page<NoticeDTO> notices = noticeService.getNotices(pageDTO);

        model.addAttribute("pageinfo", notices);
        model.addAttribute("searchinfo", pageDTO);

        return "admin/notice/noticeList";
    }

    @GetMapping("/noticeView/{noticeId}")
    public String noticeViewGet(Model model, @PathVariable String noticeId, RedirectAttributes redirectAttributes) {
        if (noticeId == null || noticeId.equals("0") || !noticeId.matches("^\\d+$")) {
            redirectAttributes.addFlashAttribute("errors", "조회할 수 없는 게시글입니다.");
            return "redirect:/admin/noticeList";
        }

        int id = Integer.parseInt(noticeId);

        Notice view = noticeService.view(id);

        if (view == null) {
            redirectAttributes.addFlashAttribute("errors", "조회할 수 없는 게시글입니다.");
            return "redirect:/admin/noticeList";
        }

        model.addAttribute("item", view);

        return "admin/notice/noticeView";
    }

    @GetMapping("/noticeRegist")
    public String noticeRegistGet(Model model, RedirectAttributes redirectAttributes) {
        return "admin/notice/noticeRegist";
    }

    @PostMapping("/noticeRegist")
    public String noticeRegistPost(@Valid NoticeDTO noticeDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes
            , HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors().get(0).getDefaultMessage());
            redirectAttributes.addFlashAttribute("item", noticeDTO);
            return "redirect:/admin/noticeRegist";
        }


        noticeDTO.setAdminId(session.getAttribute("loginAdminId").toString());

        adminService.insertNotice(noticeDTO);

        return "redirect:/admin/noticeList";
    }

    @GetMapping("/noticeModify/{noticeId}")
    public String noticeModifyGet(Model model, @PathVariable String noticeId, RedirectAttributes redirectAttributes) {
        if (noticeId == null || noticeId.equals("0") || !noticeId.matches("^\\d+$")) {
            redirectAttributes.addFlashAttribute("errors", "조회할 수 없는 게시글입니다.");
            return "redirect:/admin/noticeList";
        }

        int id = Integer.parseInt(noticeId);

        Notice view = noticeService.view(id);

        if (view == null) {
            redirectAttributes.addFlashAttribute("errors", "조회할 수 없는 게시글입니다.");
            return "redirect:/admin/noticeList";
        }

        model.addAttribute("item", view);

        return "admin/notice/noticeModify";
    }

    @PostMapping("/noticeModify/{noticeId}")
    public String noticeModifyPost(@Valid NoticeDTO noticeDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes
            , @PathVariable String noticeId
            , HttpSession session) {
        if (noticeId == null || noticeId.equals("0") || !noticeId.matches("^\\d+$")) {
            redirectAttributes.addFlashAttribute("errors", "조회할 수 없는 게시글입니다.");
            return "redirect:/admin/noticeList";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/admin/noticeModify";
        }
        noticeDTO.setAdminId((String) session.getAttribute("loginAdminId"));

        adminService.modifyNotice(noticeDTO);

        return "redirect:/admin/noticeList";
    }

    @GetMapping("/noticeDelete/{noticeId}")
    public String noticeDeleteGet(Model model, @PathVariable String noticeId, RedirectAttributes redirectAttributes
    , HttpSession session) {
        if (noticeId == null || noticeId.equals("0") || !noticeId.matches("^\\d+$")) {
            redirectAttributes.addFlashAttribute("errors", "조회할 수 없는 게시글입니다.");
            return "redirect:/admin/noticeList";
        }
        int id = Integer.parseInt(noticeId);
        String result = adminService.deleteNotice(id, (String)session.getAttribute("loginAdminId"));
        log.info("========================="+result);
        redirectAttributes.addFlashAttribute("errors", result);
        return "redirect:/admin/noticeList";
    }

}
