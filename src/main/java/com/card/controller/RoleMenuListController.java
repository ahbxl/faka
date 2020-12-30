package com.card.controller;

import com.card.entity.MenuList;
import com.card.entity.RoleMenuList;
import com.card.entity.vo.Result;
import com.card.service.MenuListService;
import com.card.service.RoleMenuListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/roleMenuList")
public class RoleMenuListController {
    @Autowired
    private RoleMenuListService roleMenuListService;
    @Autowired
    private MenuListService menuListService;


    @PostMapping("/selectList")
    public Result<Object> selectByRoleId(@RequestBody RoleMenuList roleMenuList) {
        List<RoleMenuList> roleMenuLists = roleMenuListService.lambdaQuery()
                .eq(roleMenuList.getRoleId() != null, RoleMenuList::getRoleId, roleMenuList.getRoleId())
                .list();
        List<Long> collect = roleMenuLists.stream().map(RoleMenuList::getMenuListId).collect(Collectors.toList());
        List<MenuList> menuLists = menuListService.lambdaQuery().in(MenuList::getId, collect).list();
        return Result.success(menuLists);
    }
}