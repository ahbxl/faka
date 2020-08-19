package com.card.service;

import com.card.entity.domain.Order;

import java.util.List;

public interface OrderService {
    void orderDeleteByIds(List<Long> ids);

    void orderUpdateById(Long id, Order order);

    void orderInsert(Order order);

    void orderDeleteByState(Integer state);

    void orderUpdateStateByOutTradeNo(String outTradeNo, Integer state);

    List<Order> selectByState(Integer state);
}
