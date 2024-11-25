package net.fullstack7.mooc.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.dto.MemberDTO;
import net.fullstack7.mooc.service.MemberServiceIf;
import net.fullstack7.mooc.util.CookieUtil;
import net.fullstack7.mooc.util.JSFunc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.URLDecoder;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
@Log4j2
public class LoginController extends HttpServlet {
    private final MemberServiceIf memberService;
    private CookieUtil cUtil = new CookieUtil();
    private JSFunc JSFunc = new JSFunc();

    @GetMapping("/login")
    public String login(HttpSession session, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
        if (session.getAttribute("memberId") != null) {
            redirectAttributes.addFlashAttribute("errors", "이미 로그인된 상태입니다. 회원가입 페이지에 접근할 수 없습니다.");
            return "redirect:/";
        }
        String referer = request.getHeader("referer");
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("memberId") String memberId, @RequestParam("password") String password, HttpSession session, Model model) {
        MemberDTO memberDTO = memberService.loginMember(memberId, password);
        if (memberDTO != null) {
            session.setAttribute("memberId", memberDTO.getMemberId());
            session.setAttribute("password", memberDTO.getPassword());
            String redirectURL = (String) session.getAttribute("redirectAfterLogin");
            if (redirectURL != null) {
                try {
                    session.removeAttribute("redirectAfterLogin");
                    redirectURL = URLDecoder.decode(URLDecoder.decode(redirectURL, "UTF-8"), "UTF-8");
                    log.info("redirectURL:" + redirectURL);
                    if (redirectURL.contains("login/regist") || redirectURL.contains("login/login")) {
                        return "redirect:/";
                    }
                    return "redirect:" + URLDecoder.decode(URLDecoder.decode(redirectURL, "UTF-8"), "UTF-8");
                } catch (Exception e) {
                    log.error(e);
                }
                return "redirect:/";
            } else {
                model.addAttribute("errors", "아이디 또는 비밀번호가 일치하지 않습니다.");
                return "login/login";
            }

        }
        return null;

    }
}