package com.card.controller;

import com.card.command.admin.LoginCommand;
import com.card.entity.vo.ResultVO;
import com.card.service.AdminService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    @ResponseBody
    public ResultVO<Object> findByUsernameAndPassword(@RequestBody LoginCommand command) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(command.getUsername(), command.getPassword());
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            return ResultVOUtil.success("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            return ResultVOUtil.success("密码错误");
        }
        return ResultVOUtil.success("登录失败");
    }

    @PostMapping("/countUsername/{username}")
    @ResponseBody
    public ResultVO<Object> countUsername(@PathVariable String username) {
        return ResultVOUtil.success(adminService.countByUsername(username));
    }
}