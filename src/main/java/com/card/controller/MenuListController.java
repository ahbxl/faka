package com.card.controller;

import com.card.command.menulist.MenuListACommand;
import com.card.service.MenuListService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/menuList")
public class MenuListController {
    @Autowired
    private MenuListService menuListService;

    /**
     * 查看所有的菜单信息（需要考虑到管理员或者用户登录之后所看到的菜单列表）
     *
     * @return
     */
    @PostMapping("/findAll")
    public ResultVO<Object> findAll() {
        List<MenuListACommand> menuListACommands = menuListService.findByParentNull();
        for (MenuListACommand menuListACommand : menuListACommands) {
            menuListACommand.setMenuListBCommands(menuListService.findByParent(menuListACommand.getId()));
        }
        return ResultVOUtil.success(menuListACommands);
    }
}
