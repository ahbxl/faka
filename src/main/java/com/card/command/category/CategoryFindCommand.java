package com.card.command.category;

import lombok.Data;

@Data
public class CategoryFindCommand {
    private String name;
    private Boolean state;
    private Long startTime;
    private Long endTime;
}
