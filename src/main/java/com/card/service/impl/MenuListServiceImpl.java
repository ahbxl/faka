package com.card.service.impl;

import com.card.command.menulist.MenuListACommand;
import com.card.command.menulist.MenuListBCommand;
import com.card.dao.MenuListDao;
import com.card.service.MenuListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuListServiceImpl implements MenuListService {
    @Autowired
    private MenuListDao menuListDao;

    @Override
    public List<MenuListBCommand> findByParent(Long parent) {
        return menuListDao.findByParent(parent);
    }

    @Override
    public List<MenuListACommand> findByParentNull() {
        return menuListDao.findByParentNull();
    }
}