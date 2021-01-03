package com.card.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.card.entity.MenuList;
import com.card.entity.User;
import com.card.entity.vo.MenuListVO;
import com.card.entity.vo.Result;
import com.card.security.SecurityUtil;
import com.card.service.MenuListService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/menuList")
public class MenuListController {
    @Autowired
    private MenuListService menuListService;

    @PostMapping("/selectList")
    public Result<Object> selectList(@RequestBody MenuListVO MenuListVO) {
        return Result.success(menuListService.selectList(SecurityUtil.getCurrentUser().getRoleId()));
    }

    @PostMapping("/selectPage")
    public Result<Object> selectPage(@RequestBody MenuListVO MenuListVO) {
        return Result.success(menuListService.selectPage(MenuListVO));
    }

    @PostMapping("/removeByIds")
    public Result<Object> removeByIds(@RequestBody MenuListVO MenuListVO) {
        menuListService.removeByIds(MenuListVO.getIds());
        return Result.success();
    }

    @PostMapping("/saveOrUpdate")
    public Result<Object> saveOrUpdate(@RequestBody MenuList menuList) {
        List<MenuList> menuLists = menuListService.lambdaQuery()
                .eq(MenuList::getName, menuList.getName())
                .or()
                .eq(MenuList::getPath, menuList.getPath())
                .ne(menuList.getId() != null, MenuList::getId, menuList.getId())
                .list();
        if (CollectionUtil.isNotEmpty(menuLists)) {
            return Result.fail("菜单名称或者路由已存在");
        }
        menuListService.saveOrUpdate(menuList);
        return Result.success();
    }
}
