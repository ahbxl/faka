package com.card.controller;

import com.card.entity.AliPayConfig;
import com.card.entity.Order;
import com.card.entity.vo.AliPayConfigVO;
import com.card.entity.vo.OrderVO;
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

    /**
     * 通过id修改支付配置
     * 需要管理员权限
     *
     * @param aliPayConfig
     * @return
     */
    @PostMapping("/admin/updateById")
    public ResultVO<Object> updateById(@RequestBody AliPayConfig aliPayConfig) {
        AliPayConfig aliPayConfigById = aliPayService.selectById(aliPayConfig.getId());
        if (aliPayConfigById == null) {
            return ResultVOUtil.fail("不存在该配置");
        }
        aliPayService.updateById(aliPayConfig);
        return ResultVOUtil.success();
    }

    /**
     * 通过id查询支付配置
     * 需要管理员权限
     *
     * @param aliPayConfigVO
     * @return
     */
    @PostMapping("/admin/selectById")
    public ResultVO<Object> selectById(@RequestBody AliPayConfigVO aliPayConfigVO) {
        return ResultVOUtil.success(aliPayService.selectById(aliPayConfigVO.getId()));
    }

    /**
     * 通过订单号查询订单
     *
     * @param order 订单
     * @return
     */
    @PostMapping("/selectByOutTradeNo")
    public ResultVO<Object> selectByOutTradeNo(@RequestBody Order order) {
        String state = aliPayService.selectByOutTradeNo(order.getOutTradeNo());
        if ("TRADE_SUCCESS".equalsIgnoreCase(state)) {
            orderService.orderUpdateStateByOutTradeNo(order.getOutTradeNo(), 1);
        }
        return ResultVOUtil.success(state);
    }

    /**
     * 通过订单号取消交易
     *
     * @param orderVO
     * @return
     */
    @PostMapping("/cancelTrade")
    public ResultVO<Object> cancelTrade(@RequestBody OrderVO orderVO) {
        return ResultVOUtil.success(aliPayService.cancelTrade(orderVO.getOutTradeNo()));
    }
}