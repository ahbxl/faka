package com.card.command.order;

import lombok.Data;

@Data
public class OrderSelectCommand {
    private String subject; // 订单标题
    private String outTradeNo; // 订单号
    private Boolean state; // 状态
    private Long startTime; // 开始时间
    private Long endTime; // 结束时间
}
