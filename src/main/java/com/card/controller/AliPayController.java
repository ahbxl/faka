package com.card.controller;

import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.card.command.alipay.AliPayCommand;
import com.card.entity.vo.ResultVO;
import com.card.service.AliPayService;
import com.card.service.OrderService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/aliPay")
public class AliPayController {
    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/faceToFace")
    public ResultVO<Object> faceToFace(@RequestBody AliPayCommand aliPayCommand) {
        aliPayCommand.validate();
        log.info("当面付支付调用");
        AlipayTradePrecreateResponse response = aliPayService.faceToFace(aliPayCommand.getSubject(), aliPayCommand.getOutTradeNo(), aliPayCommand.getTotalAmount());
        if (ResponseChecker.success(response)) {
            orderService.orderInsert(aliPayCommand.doBuildOrder());
            log.info("生成支付二维码链接");
            return ResultVOUtil.success(response.qrCode);
        }
        return ResultVOUtil.success("支付调用失败，原因：" + response.msg + "，" + response.subMsg);
    }

    @PostMapping("/queryOutTradeNo/{outTradeNo}")
    public ResultVO<Object> queryOutTradeNo(@PathVariable("outTradeNo") String outTradeNo) {
        String state = aliPayService.queryTrade(outTradeNo);
        if ("TRADE_SUCCESS".equalsIgnoreCase(state)) {
            orderService.orderUpdateStateByOutTradeNo(outTradeNo, 1);
        }
        return ResultVOUtil.success(aliPayService.queryTrade(outTradeNo));
    }

    @PostMapping("/cancelTrade/{outTradeNo}")
    public ResultVO<Object> cancelTrade(@PathVariable("outTradeNo") String outTradeNo) {
        return ResultVOUtil.success(aliPayService.cancelTrade(outTradeNo));
    }
}