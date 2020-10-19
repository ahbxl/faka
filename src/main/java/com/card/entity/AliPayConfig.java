package com.card.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("ali_pay_config")
public class AliPayConfig implements Serializable {
    private static final long serialVersionUID = -5565956842416722322L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Long userId;
    @TableField("app_id")
    private String appId;
    @TableField("merchant_private_key")
    private String merchantPrivateKey;
    @TableField("ali_pay_public_key")
    private String aliPayPublicKey;
    @TableField("state")
    private Integer state;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
}