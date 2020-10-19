package com.card.controller;

import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.card.command.alipay.AliPayCommand;
import com.card.entity.AliPayConfig;
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

    /**
     * 通过id修改支付配置
     * 需要管理员权限
     *
     * @param id           id
     * @param aliPayConfig 支付配置对象
     * @return
     */
    @PostMapping("/admin/updateById/{id}")
    public ResultVO<Object> updateById(@PathVariable("id") Long id, @RequestBody AliPayConfig aliPayConfig) {
        AliPayConfig aliPayConfigById = aliPayService.selectById(id);
        if (aliPayConfigById == null) {
            return ResultVOUtil.fail("不存在该配置");
        }
        aliPayService.updateById(id, aliPayConfig);
        return ResultVOUtil.success();
    }

    /**
     * 通过id查询支付配置
     * 需要管理员权限
     *
     * @param id id
     * @return
     */
    @PostMapping("/admin/selectById/{id}")
    public ResultVO<Object> selectById(@PathVariable("id") Long id) {
        AliPayConfig aliPayConfigById = aliPayService.selectById(id);
        if (aliPayConfigById == null) {
            return ResultVOUtil.fail("不存在该配置");
        }
        return ResultVOUtil.success(aliPayConfigById);
    }

    /**
     * 支付宝当面付接口
     *
     * @param aliPayCommand
     * @return
     */
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

    /**
     * 通过订单号查询订单
     *
     * @param outTradeNo 订单号
     * @return
     */
    @PostMapping("/queryOutTradeNo/{outTradeNo}")
    public ResultVO<Object> queryOutTradeNo(@PathVariable("outTradeNo") String outTradeNo) {
        String state = aliPayService.queryTrade(outTradeNo);
        if ("TRADE_SUCCESS".equalsIgnoreCase(state)) {
            orderService.orderUpdateStateByOutTradeNo(outTradeNo, 1);
        }
        return ResultVOUtil.success(aliPayService.queryTrade(outTradeNo));
    }

    /**
     * 通过订单号取消交易
     *
     * @param outTradeNo 订单号
     * @return
     */
    @PostMapping("/cancelTrade/{outTradeNo}")
    public ResultVO<Object> cancelTrade(@PathVariable("outTradeNo") String outTradeNo) {
        return ResultVOUtil.success(aliPayService.cancelTrade(outTradeNo));
    }
}