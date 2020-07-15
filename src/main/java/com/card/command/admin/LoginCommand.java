package com.card.command.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginCommand {
    private String username;
    private String password;
}
