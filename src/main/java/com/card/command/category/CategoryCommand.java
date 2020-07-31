package com.card.command.category;

import lombok.Data;

@Data
public class CategoryCommand {
    private Long id;
    private String name;
    private Long parent;
    private Integer state;
    private Long createTime;
}