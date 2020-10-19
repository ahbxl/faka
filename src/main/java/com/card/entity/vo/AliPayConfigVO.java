package com.card.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AliPayConfigVO extends PageVO implements Serializable {
    private static final long serialVersionUID = -21865525976214117L;
    private Long id;
    private Long userId;
    private String appId;
    private String merchantPrivateKey;
    private String aliPayPublicKey;
    private Integer state;
    private Date createTime;
    private Date updateTime;
}