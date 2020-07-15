package com.card.entity.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@TableName("order")
public class Order implements Serializable {
    private static final long serialVersionUID = -6692823745412833806L;
    @TableId("id")
    private Integer id;
    @TableField("product_id")
    private Integer productId;
    @TableField("quantity")
    private Integer quantity;
    @TableField("subject")
    private String subject;
    @TableField("outTradeNo")
    private String outTradeNo;
    @TableField("create_time")
    private Long createTime;
}