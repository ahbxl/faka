package com.card.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.OrderDao;
import com.card.entity.Order;
import com.card.entity.vo.OrderVO;
import com.card.enu.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService extends ServiceImpl<OrderDao, Order>  {
    @Autowired
    private OrderDao orderDao;

    public void deleteBatchIds(List<Long> ids) {
        orderDao.deleteBatchIds(ids);
    }

    public void insert(Order order) {
        orderDao.insert(order);
    }

    public void deleteByState(Integer state) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", OrderState.Not_Pay.getValue());
        orderDao.delete(queryWrapper);
    }

    public void orderUpdateStateByOutTradeNo(String outTradeNo, Integer state) {
        orderDao.orderUpdateStateByOutTradeNo(outTradeNo, state);
    }

    public IPage<Order> selectPage(OrderVO orderVO) {
        Page<Order> orderPage = new Page<>(orderVO.getPageNum(), orderVO.getPageSize());
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.like(!StringUtils.isBlank(orderVO.getSubject()), "subject", orderVO.getSubject());
        wrapper.like(!StringUtils.isBlank(orderVO.getOutTradeNo()), "outTradeNo", orderVO.getOutTradeNo());
        wrapper.eq(null != orderVO.getState(), "state", orderVO.getState());
        wrapper.between(null != orderVO.getStartTime() && null != orderVO.getEndTime(), "create_time", orderVO.getStartTime(), orderVO.getEndTime());
        wrapper.orderByDesc("create_time");
        return orderDao.selectPage(orderPage, wrapper);
    }

    public Order selectById(Long id) {
        return orderDao.selectById(id);
    }

    public List<Order> selectByState(Integer state) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", state);
        return orderDao.selectList(queryWrapper);
    }

    public Order selectByOutTradeNo(String outTradeNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("outTradeNo", outTradeNo);
        return orderDao.selectOne(queryWrapper);
    }
}