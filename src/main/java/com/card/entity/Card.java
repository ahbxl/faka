package com.card.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("card")
public class Card implements Serializable {
    private static final long serialVersionUID = 8632318572265851654L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("product_id")
    private Long productId;
    @TableField(exist = false)
    private String productName;
    @TableField("content")
    private String content;
    @TableField("state")
    private Integer state; // 状态 0/未售出 1/已售出 默认未售出
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
}