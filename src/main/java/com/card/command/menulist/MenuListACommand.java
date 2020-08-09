package com.card.command.menulist;

import lombok.Data;

import java.util.List;

@Data
public class MenuListACommand {
    private Long id;
    private String name;
    private List<MenuListBCommand> menuListBCommands;
}