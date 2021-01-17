package com.card.controller;

import cn.hutool.core.util.StrUtil;
import com.card.entity.AliPayConfig;
import com.card.entity.Order;
import com.card.entity.vo.AliPayConfigVO;
import com.card.entity.vo.OrderVO;
import com.card.entity.vo.Result;
import com.card.security.utils.SecurityUtil;
import com.card.service.AliPayService;
import com.card.service.OrderService;
import com.card.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/aliPay")
public class AliPayController {
    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    /**
     * 修改或者添加支付配置
     *
     * @param aliPayConfig
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Result<Object> saveOrUpdate(@RequestBody AliPayConfig aliPayConfig) {
        List<Long> longs = userService.selectUserIds(SecurityUtil.getCurrentUser().getId(), true);
        Integer count = aliPayService.lambdaQuery()
                .eq(StrUtil.isNotBlank(aliPayConfig.getAppId()), AliPayConfig::getAppId, aliPayConfig.getAppId())
                .in(AliPayConfig::getUserId, longs)
                .ne(aliPayConfig.getId() != null, AliPayConfig::getId, aliPayConfig.getId())
                .count();
        if (count > 0) return Result.fail("AppId已存在");
        if (aliPayConfig.getId() == null) {
            aliPayConfig.setUserId(SecurityUtil.getCurrentUser().getId());
        }
        aliPayService.lambdaUpdate().in(AliPayConfig::getUserId, longs).update(aliPayConfig);
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
    @PostMapping("/getById")
    public Result<Object> getById(@RequestBody AliPayConfigVO aliPayConfigVO) {
        List<Long> longs = userService.selectUserIds(SecurityUtil.getCurrentUser().getId(), true);
        AliPayConfig aliPayConfig = aliPayService.lambdaQuery().in(AliPayConfig::getUserId, longs)
                .eq(AliPayConfig::getId, aliPayConfigVO.getId()).one();
        return Result.success(aliPayConfig);
    }

    /**
     * 通过订单号查询订单
     *
     * @param orderVO
     * @return
     */
    @PostMapping("/selectByOutTradeNo")
    public Result<Object> selectByOutTradeNo(@RequestBody OrderVO orderVO) {
        return Result.success(aliPayService.selectByOutTradeNo(orderVO.getOutTradeNo()));
    }

    /**
     * 通过订单号取消交易
     *
     * @param orderVO
     * @return
     */
    @PostMapping("/cancelTrade")
    public Result<Object> cancelTrade(@RequestBody OrderVO orderVO) {
        Order order = orderService.selectByOutTradeNo(orderVO.getOutTradeNo());
        if (order == null) return Result.fail("不存在该订单");
        List<Long> longs = userService.selectUserIds(SecurityUtil.getCurrentUser().getId(), true);
        if (!longs.contains(order.getCreator())) {
            return Result.fail("您暂无权限");
        }
        aliPayService.cancelTrade(orderVO.getOutTradeNo());
        log.info("用户{}取消了订单，订单号号为{}", SecurityUtil.getCurrentUser().getId(), orderVO.getOutTradeNo());
        return Result.success();
    }

    @PostMapping("/selectPage")
    public Result<Object> selectPage(@RequestBody AliPayConfigVO aliPayConfigVO) {
        return Result.success(aliPayService.selectPage(aliPayConfigVO));
    }

    @PostMapping("/removeById")
    public Result<Object> removeById(@RequestBody AliPayConfig aliPayConfig) {
        List<Long> longs = userService.selectUserIds(SecurityUtil.getCurrentUser().getId(), true);
        aliPayService.lambdaUpdate().in(AliPayConfig::getUserId, longs)
                .eq(AliPayConfig::getId, aliPayConfig.getId())
                .remove();
        return Result.success();
    }
}