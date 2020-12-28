package com.card.controller;

import com.card.service.RoleMenuListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/roleMenuList")
public class RoleMenuListController {
    @Autowired
    private RoleMenuListService roleMenuListService;
    // todo 角色关联的菜单相关的功能等待开发，涉及到ShiroConfig中的接口请求权限

}
