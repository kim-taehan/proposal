package org.developx.proposal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class SampleController {

    @RequestMapping("")
    public String home(){
        log.info("Home Controller");
        return "home";
    }

    @RequestMapping("login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm("", ""));
        log.info("Login call");
        return "login/loginForm";
    }
}
