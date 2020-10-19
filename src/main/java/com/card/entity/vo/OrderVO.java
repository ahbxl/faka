package com.card.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderVO extends PageVO implements Serializable {
    private static final long serialVersionUID = -3640807116600926284L;
    private Long id;
    private Long productId;
    private Long quantity;
    private String subject;
    private String outTradeNo;
    private String totalAmount;
    private Integer state;
    private Date createTime;
    private Date updateTime;
}