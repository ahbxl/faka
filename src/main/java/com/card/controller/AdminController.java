package com.card.controller;

import com.card.command.admin.LoginCommand;
import com.card.entity.domain.Admin;
import com.card.entity.vo.ResultVO;
import com.card.service.AdminService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
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
        Admin admin = adminService.findByUsernameAndPassword(command.getUsername(), command.getPassword());
        if (admin != null) {
            return ResultVOUtil.success(admin);
        }
        return ResultVOUtil.success("用户名或者密码输入错误！");
    }

    @PostMapping("/countUsername/{username}")
    @ResponseBody
    public ResultVO<Object> countUsername(@PathVariable String username) {
        return ResultVOUtil.success(adminService.countByUsername(username));
    }
}