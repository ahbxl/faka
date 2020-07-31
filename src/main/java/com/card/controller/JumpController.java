package com.card.controller;

import com.card.entity.domain.Admin;
import com.card.entity.vo.ResultVO;
import com.card.service.AdminService;
import com.card.util.JwtUtil;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

@RestController
@Slf4j
public class JumpController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResultVO<Object> findByUsernameAndPassword(@RequestBody Admin admin) {
        admin.validate();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        try {
            subject.login(usernamePasswordToken);
            // 生成token，token有效时间为30分钟
            String token = JwtUtil.createJWT(String.valueOf(new Date()), admin.getUsername(), 3600000L);
            // 将用户户名和token返回
            HashMap<String, String> map = new HashMap<>();
            map.put("username", admin.getUsername());
            map.put("token", token);
            return ResultVOUtil.success(map);
        } catch (UnknownAccountException e) {
            return ResultVOUtil.success("登陆失败！用户名或密码不正确");
        }
    }

    @PostMapping("/countUsername/{username}")
    public ResultVO<Object> countUsername(@PathVariable String username) {
        return ResultVOUtil.success(adminService.countByUsername(username));
    }
}