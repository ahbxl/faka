package com.card.task;

import com.card.entity.Order;
import com.card.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class OrderTask {
    @Autowired
    private OrderService orderService;

    /**
     * 每天凌晨2点删除未支付的订单
     */
    @Scheduled(cron = "0 0 2 1 * ?")
    public void deleteOrderByState() {
        List<Order> orders = orderService.selectByState(0);
        List<String> outTradeNos = orders.stream().map(Order::getOutTradeNo).collect(Collectors.toList());
        // 将删除的订单号输出到日志
        log.info(outTradeNos.toString());
        // 每月的1日的凌晨1点删除数据库中未支付的订单信息
        log.info("开始执行删除数据库中未支付的订单信息");
        orderService.deleteByState(0);
    }
}