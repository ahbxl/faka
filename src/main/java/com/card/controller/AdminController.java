package com.card.controller;

import com.card.entity.vo.ResultVO;
import com.card.service.AdminService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/countUsername/{username}")
    public ResultVO<Object> countUsername(@PathVariable String username) {
        return ResultVOUtil.success(adminService.countByUsername(username));
    }
}