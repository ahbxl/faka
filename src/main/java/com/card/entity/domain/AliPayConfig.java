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
@TableName("ali_pay_config")
public class AliPayConfig implements Serializable {
    private static final long serialVersionUID = -5565956842416722322L;
    @TableId("id")
    private Long id;
    @TableField("app_id")
    private String appId;
    @TableField("merchant_private_key")
    private String merchantPrivateKey;
    @TableField("ali_pay_public_key")
    private String aliPayPublicKey;
}