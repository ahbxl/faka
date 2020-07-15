package com.card.controller;

import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.card.entity.vo.ResultVO;
import com.card.service.AliPayService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/aliPay")
public class AliPayController {
    @Autowired
    private AliPayService aliPayService;

    @PostMapping("/faceToFace")
    @ResponseBody
    public ResultVO<Object> faceToFace() throws Exception {
        log.info("当面付支付调用");
//        AlipayTradePrecreateResponse response = aliPayService.faceToFace(aliPayCommand.getSubject(), aliPayCommand.getOutTradeNo(), aliPayCommand.getTotalAmount());
        AlipayTradePrecreateResponse response = aliPayService.faceToFace("iphone 11", "2234567890", "0.01");
        if (ResponseChecker.success(response)) {
            return ResultVOUtil.success(response.qrCode);
        }
        return ResultVOUtil.success("支付调用失败，原因：" + response.msg + "，" + response.subMsg);
    }
}