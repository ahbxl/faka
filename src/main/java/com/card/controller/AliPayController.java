package com.card.controller;

import com.card.entity.AliPayConfig;
import com.card.entity.Order;
import com.card.entity.vo.AliPayConfigVO;
import com.card.entity.vo.OrderVO;
import com.card.entity.vo.Result;
import com.card.service.AliPayService;
import com.card.service.OrderService;
import com.card.service.UserService;
import com.card.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/aliPay")
public class AliPayController {
    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    /**
     * 通过id修改支付配置
     * 需要管理员权限
     *
     * @param aliPayConfig
     * @return
     */
    @PostMapping("/token/updateById")
    public Result<Object> updateById(@RequestBody AliPayConfig aliPayConfig) {
        AliPayConfig aliPayConfig1 = aliPayService.selectById(aliPayConfig.getId());
        if (aliPayConfig1 == null) {
            return Result.fail("不存在该配置");
        }
        List<Long> longs = userService.selectIdsByParentId(SecurityUtil.getCurrentUser().getId());
        if (!longs.contains(aliPayConfig1.getUserId())) {
            return Result.fail("您暂无权限");
        }
        aliPayService.updateById(aliPayConfig);
        log.info("用户{}更新了{}", SecurityUtil.getCurrentUser().getId(), aliPayConfig);
        return Result.success();
    }

    /**
     * 通过id查询支付配置
     * 需要管理员权限
     *
     * @param aliPayConfigVO
     * @return
     */
    @PostMapping("/token/selectById")
    public Result<Object> selectById(@RequestBody AliPayConfigVO aliPayConfigVO) {
        AliPayConfig aliPayConfig = aliPayService.selectById(aliPayConfigVO.getId());
        if (aliPayConfig == null) {
            return Result.fail("不存在该配置");
        }
        List<Long> longs = userService.selectIdsByParentId(SecurityUtil.getCurrentUser().getId());
        if (!longs.contains(aliPayConfig.getUserId())) {
            return Result.fail("您暂无权限");
        }
        return Result.success(aliPayConfig);
    }

    /**
     * 通过订单号查询订单
     *
     * @param orderVO
     * @return
     */
    @PostMapping("/token/selectByOutTradeNo")
    public Result<Object> selectByOutTradeNo(@RequestBody OrderVO orderVO) {
        Order order = orderService.selectByOutTradeNo(orderVO.getOutTradeNo());
        if (order == null) {
            return Result.fail("不存在该订单");
        }
        List<Long> longs = userService.selectIdsByParentId(SecurityUtil.getCurrentUser().getId());
        if (!longs.contains(order.getCreator())) {
            return Result.fail("您暂无权限");
        }
        return Result.success(aliPayService.selectByOutTradeNo(orderVO.getOutTradeNo()));
    }

    /**
     * 通过订单号取消交易
     *
     * @param orderVO
     * @return
     */
    @PostMapping("/token/cancelTrade")
    public Result<Object> cancelTrade(@RequestBody OrderVO orderVO) {
        Order order = orderService.selectByOutTradeNo(orderVO.getOutTradeNo());
        if (order == null) {
            return Result.fail("不存在该订单");
        }
        List<Long> longs = userService.selectIdsByParentId(SecurityUtil.getCurrentUser().getId());
        if (!longs.contains(order.getCreator())) {
            return Result.fail("您暂无权限");
        }
        log.info("用户{}取消了订单，订单号号为{}", SecurityUtil.getCurrentUser().getId(), orderVO.getOutTradeNo());
        return Result.success(aliPayService.cancelTrade(orderVO.getOutTradeNo()));
    }
}