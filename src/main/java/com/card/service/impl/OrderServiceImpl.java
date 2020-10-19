package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.card.dao.OrderDao;
import com.card.entity.Order;
import com.card.entity.vo.OrderVO;
import com.card.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public void deleteBatchIds(List<Long> ids) {
        orderDao.deleteBatchIds(ids);
    }

    @Override
    public void updateById(Order order) {
        orderDao.updateById(order);
    }

    @Override
    public void insert(Order order) {
        orderDao.insert(order);
    }

    @Override
    public void deleteByState(Integer state) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 0);
        orderDao.delete(queryWrapper);
    }

    @Override
    public void orderUpdateStateByOutTradeNo(String outTradeNo, Integer state) {
        orderDao.orderUpdateStateByOutTradeNo(outTradeNo, state);
    }

    @Override
    public IPage<Order> selectPage(OrderVO orderVO) {
        Page<Order> orderPage = new Page<>(orderVO.getPageNum(), orderVO.getPageSize());
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(orderVO.getSubject())) {
            wrapper.like("subject", orderVO.getSubject());
        }
        if (!StringUtils.isBlank(orderVO.getOutTradeNo())) {
            wrapper.like("outTradeNo", orderVO.getOutTradeNo());
        }
        if (null != orderVO.getState()) {
            wrapper.eq("state", orderVO.getState());
        }
        if (null != orderVO.getStartTime() && null != orderVO.getEndTime()) {
            wrapper.between("create_time", orderVO.getStartTime(), orderVO.getEndTime());
        }
        wrapper.orderByDesc("create_time");
        return orderDao.selectPage(orderPage, wrapper);
    }

    @Override
    public Order selectOne(Long id) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return orderDao.selectOne(wrapper);
    }

    @Override
    public List<Order> selectByState(Integer state) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",state);
        return orderDao.selectList(queryWrapper);
    }
}