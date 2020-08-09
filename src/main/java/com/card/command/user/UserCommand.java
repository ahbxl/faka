package com.card.command.user;

import lombok.Data;

@Data
public class UserCommand {
    private String username;
    private String email;
    private Boolean state;
}
