package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.card.entity.Order;
import com.card.entity.User;
import com.card.entity.vo.OrderVO;

import java.util.List;

public interface OrderService extends IService<Order> {
    void deleteBatchIds(List<Long> ids);

    void insert(Order order);

    void deleteByState(Integer state);

    void orderUpdateStateByOutTradeNo(String outTradeNo, Integer state);

    IPage<Order> selectPage(OrderVO orderVO);

    Order selectOne(Long id);

    List<Order> selectByState(Integer state);
}
