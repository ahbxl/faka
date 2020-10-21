package com.card.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.card.util.ResultVOUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("`order`")
public class Order implements Serializable {
    private static final long serialVersionUID = -6692823745412833806L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("product_id")
    private Long productId;
    @TableField("creator")
    private Long creator;
    @TableField("quantity")
    private Long quantity;
    @TableField("subject")
    private String subject;
    @TableField("outTradeNo")
    private String outTradeNo;
    @TableField("total_amount")
    private String totalAmount;
    @TableField("state")
    private Integer state;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
}