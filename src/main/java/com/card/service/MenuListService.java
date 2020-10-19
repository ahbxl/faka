package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.entity.MenuList;
import com.card.entity.vo.MenuListVO;

public interface MenuListService {
    IPage<MenuList> selectPage(MenuListVO menuListVO);
}