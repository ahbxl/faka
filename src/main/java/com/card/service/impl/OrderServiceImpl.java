package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.card.dao.OrderDao;
import com.card.entity.domain.Order;
import com.card.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public void orderDeleteByIds(List<Long> ids) {
        orderDao.orderDeleteByIds(ids);
    }

    @Override
    public void orderUpdateById(Long id, Order order) {
        orderDao.orderUpdateById(id, order);
    }

    @Override
    public void orderInsert(Order order) {
        orderDao.orderInsert(order);
    }

    @Override
    public void orderDeleteByState(Integer state) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 0);
        orderDao.delete(queryWrapper);
    }

    @Override
    public void orderUpdateStateByOutTradeNo(String outTradeNo, Integer state) {
        orderDao.orderUpdateStateByOutTradeNo(outTradeNo, state);
    }
}