package com.card.command.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCommand {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer state;
    private Long categoryId;
    private Long createTime;
}