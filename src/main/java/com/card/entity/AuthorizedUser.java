package com.card.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizedUser {
    private Long id;
    private String username;
    private String ip;
}