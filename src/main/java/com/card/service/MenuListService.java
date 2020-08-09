package com.card.service;

import com.card.command.menulist.MenuListACommand;
import com.card.command.menulist.MenuListBCommand;

import java.util.List;

public interface MenuListService {
    List<MenuListBCommand> findByParent(Long parent);

    List<MenuListACommand> findByParentNull();
}