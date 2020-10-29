package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.card.entity.MenuList;
import com.card.entity.User;
import com.card.entity.vo.MenuListVO;

import java.util.List;

public interface MenuListService extends IService<MenuList> {
    IPage<MenuList> selectPage(MenuListVO menuListVO);

    List<MenuList> selectList(MenuListVO menuListVO);

    List<MenuList> menuList(MenuListVO menuListVO);
}