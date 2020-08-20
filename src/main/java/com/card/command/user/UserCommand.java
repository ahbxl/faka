package com.card.command.user;

import lombok.Data;

@Data
public class UserCommand {
    private String username;
    private String email;
    private Boolean state;
    private Integer grade; // 用户等级 0/普通用户 1/一级代理 2/二级代理 3/三级代理
}