package com.card.controller;

import com.card.command.admin.LoginCommand;
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
        return ResultVOUtil.success(adminService.findByUsernameAndPassword(command.getUsername(), command.getPassword()));
    }

    @PostMapping("/countUsername/{username}")
    @ResponseBody
    public ResultVO<Object> countUsername(@PathVariable String username) {
        return ResultVOUtil.success(adminService.countByUsername(username));
    }
}