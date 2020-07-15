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
@TableName("card")
public class Card implements Serializable {
    private static final long serialVersionUID = 8632318572265851654L;
    @TableId("id")
    private Long id;
    @TableField("content")
    private String content;
    @TableField("state")
    private Integer state;
    @TableField("product_id")
    private Long productId;
    @TableField("create_time")
    private Long createTime;
}
