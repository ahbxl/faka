package com.card.task;

import com.card.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@Slf4j
public class OrderTask {
    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "0 0 1 1 * ?")
    public void deleteOrderByState() {
        // 每月的1日的凌晨1点删除数据库中未支付的订单信息
        log.info("开始执行删除数据库中未支付的订单信息");
        orderService.orderDeleteByState(0);
    }
}