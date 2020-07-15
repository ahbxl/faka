package com.card.service;

import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;

public interface AliPayService {
    /**
     * 当面付电脑网站支付
     *
     * @param subject     订单标题
     * @param outTradeNo  交易创建时传入的商户订单号
     * @param totalAmount 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * @return
     */
    AlipayTradePrecreateResponse faceToFace(String subject, String outTradeNo, String totalAmount) throws Exception;
}