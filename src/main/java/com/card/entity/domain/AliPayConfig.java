package com.card.entity.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName("ali_pay_config")
public class AliPayConfig {
    @TableId("id")
    private Integer id;
    @TableField("app_id")
    private String appId;
    @TableField("merchant_private_key")
    private String merchantPrivateKey;
    @TableField("ali_pay_public_key")
    private String aliPayPublicKey;
}