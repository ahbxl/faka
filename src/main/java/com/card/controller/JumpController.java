package com.card.controller;

import com.card.command.admin.LoginCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class JumpController {
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = {"/login"})
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView findByUsernameAndPassword(LoginCommand command) {
        ModelAndView modelAndView = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(command.getUsername(), command.getPassword());
        try {
            subject.login(usernamePasswordToken);
            modelAndView.setViewName("admin");
            return modelAndView;
        } catch (UnknownAccountException e) {
            modelAndView.addObject("msg", "登陆失败！用户名或密码不正确");
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }
}