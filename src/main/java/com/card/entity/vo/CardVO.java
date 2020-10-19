package com.card.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class CardVO extends Page implements Serializable {
    private static final long serialVersionUID = 6332860721283726482L;
    private Long id;
    private Long productId;
    private String content;
    private Integer state; // 状态 0/未售出 1/已售出 默认未售出
    private Date createTime;
    private Date updateTime;
}