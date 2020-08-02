package com.card.command.product;

import lombok.Data;

@Data
public class ProductFindCommand {
    private String name;
    private Integer state;
    private Long categoryId;
    private Long startTime;
    private Long endTime;
}