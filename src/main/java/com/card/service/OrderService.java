package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.command.IdsCommand;
import com.card.command.order.OrderSelectCommand;
import com.card.entity.Order;

import java.util.List;

public interface OrderService {
    void orderDeleteByIds(List<Long> ids);

    void updateById(Long id, Order order);

    void orderInsert(Order order);

    void orderDeleteByState(Integer state);

    void orderUpdateStateByOutTradeNo(String outTradeNo, Integer state);

    List<Order> selectByState(Integer state);

    IPage<Order> selectByPage(Integer pageNum, Integer pageSize, OrderSelectCommand command);

    Order selectOne(Long id);

    void deleteByIds(IdsCommand command);
}
