package com.card.controller;

import com.card.entity.vo.Result;
import com.card.entity.vo.RoleMenuListVO;
import com.card.service.MenuListService;
import com.card.service.RoleMenuListService;
import com.card.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/roleMenuList")
public class RoleMenuListController {
    @Autowired
    private RoleMenuListService roleMenuListService;


    @PostMapping("/selectPage")
    @RequiresRoles({"admin"})
    public Result<Object> selectPage(@RequestBody RoleMenuListVO roleMenuListVO) {
        return Result.success(roleMenuListService.selectPage(roleMenuListVO));
    }
}