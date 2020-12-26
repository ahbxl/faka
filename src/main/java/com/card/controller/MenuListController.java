package com.card.controller;

import com.card.entity.vo.MenuListVO;
import com.card.entity.vo.Result;
import com.card.service.MenuListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/menuList")
public class MenuListController {
    @Autowired
    private MenuListService menuListService;

    @PostMapping("/selectPage")
    public Result<Object> selectPage(@RequestBody MenuListVO MenuListVO) {
        return Result.success(menuListService.menuList(MenuListVO));
    }
}
