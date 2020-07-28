package com.card.controller;

import com.card.command.admin.LoginCommand;
import com.card.entity.vo.ResultVO;
import com.card.util.JwtUtil;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;

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

    @PostMapping("/adminLogin")
    @ResponseBody
    public ResultVO<Object> findByUsernameAndPassword(LoginCommand command) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(command.getUsername(), command.getPassword());
        try {
            subject.login(usernamePasswordToken);
            String token = JwtUtil.createJWT(String.valueOf(new Date()), command.getUsername(), 360000L);
            HashMap<String, String> map = new HashMap<>();
            map.put("username", command.getUsername());
            map.put("token", token);
            return ResultVOUtil.success(map);
        } catch (UnknownAccountException e) {
            return ResultVOUtil.success("登陆失败！用户名或密码不正确");
        }
    }
}