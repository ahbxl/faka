package com.card.command.alipay;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AliPayCommand {
    private String subject;
    private String outTradeNo;
    private String totalAmount;
}