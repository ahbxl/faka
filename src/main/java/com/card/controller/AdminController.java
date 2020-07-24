package com.card.controller;

import com.card.command.admin.LoginCommand;
import com.card.entity.domain.Admin;
import com.card.entity.vo.ResultVO;
import com.card.service.AdminService;
import com.card.util.ResultVOUtil;
import com.card.util.TokenSubjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ModelAndView findByUsernameAndPassword(LoginCommand command) {
        ModelAndView modelAndView = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(command.getUsername(), command.getPassword());
        try {
            subject.login(usernamePasswordToken);
            String key = UUID.randomUUID().toString();
            TokenSubjectUtil.saveSubject(key,subject);
            modelAndView.setViewName("admin");
            modelAndView.addObject("token",key);
            return modelAndView;
        } catch (UnknownAccountException e) {
            modelAndView.addObject("msg", "登陆失败！用户名或密码不正确");
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    @PostMapping("/countUsername/{username}")
    @ResponseBody
    public ResultVO<Object> countUsername(@PathVariable String username) {
        return ResultVOUtil.success(adminService.countByUsername(username));
    }
}