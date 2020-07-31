package com.card.command.category;

import lombok.Data;

@Data
public class CategoryFindCommand {
    private String name;
    private Integer state;
    private Long startTime;
    private Long endTime;
}
