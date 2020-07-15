package com.card.entity.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@TableName("product")
public class Product implements Serializable {
    private static final long serialVersionUID = -54169886181194401L;
    @TableId("id")
    private Long id;
    @TableField("name")
    private String name;
    @TableField("price")
    private BigDecimal price;
    @TableField("state")
    private Integer state;
    @TableField("category_id")
    private Long categoryId;
    @TableField("create_time")
    private Long createTime;
}