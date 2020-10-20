package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.card.entity.MenuList;
import com.card.entity.User;
import com.card.entity.vo.MenuListVO;

public interface MenuListService extends IService<MenuList> {
    IPage<MenuList> selectPage(MenuListVO menuListVO);
}