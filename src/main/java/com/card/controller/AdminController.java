package com.card.controller;

import com.card.command.category.CategoryCommand;
import com.card.command.category.CategoryIdsCommand;
import com.card.entity.vo.ResultVO;
import com.card.service.AdminService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/category/findByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> categoryFindByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody CategoryCommand command) {
        return ResultVOUtil.success(adminService.categoryFindByPage(pageNum, pageSize, command));
    }

    @PostMapping("/category/deleteByIds")
    public ResultVO<Object> categoryDeleteByIds(@RequestBody CategoryIdsCommand command) {
        command.validate();
        adminService.categoryDeleteByIds(command);
        return ResultVOUtil.success();
    }

    @PostMapping("/category/updateById")
    public ResultVO<Object> categoryUpdateById(@RequestBody CategoryCommand command) {
        command.validate();
        adminService.categoryUpdateById(command);
        return ResultVOUtil.success();
    }

    @PostMapping("/category/insert")
    public ResultVO<Object> categoryInsert(@RequestBody CategoryCommand command) {
        adminService.categoryInsert(command);
        return ResultVOUtil.success();
    }
}