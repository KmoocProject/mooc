package net.fullstack7.mooc.controller;

import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.service.MemberServiceIf;
import net.fullstack7.mooc.util.CookieUtil;
import net.fullstack7.mooc.util.JSFunc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
@Log4j2
public class LoginController extends HttpServlet {
//    private final MemberServiceIf MemberService;
//    private CookieUtil cUtil = new CookieUtil();
//    private JSFunc JSFunc = new JSFunc();

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/memberchose")
    public String memberchose() {
        return "login/memberchose";
    }

    @GetMapping("/memberterms")
    public String memberterms() {
        return "login/memberterms";
    }

    @GetMapping("/regist")
    public String regist() {
        return "login/regist";
    }

    @GetMapping("/finishjoin")
    public String finishjoin() {
        return "login/finishjoin";
    }


}
