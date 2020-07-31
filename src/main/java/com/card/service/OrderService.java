package com.card.service;

import com.card.entity.domain.Order;

import java.util.List;

public interface OrderService {
    void orderDeleteByIds(List<Long> ids);

    void orderUpdateById(Order order);

    void orderInsert(Order order);
}
