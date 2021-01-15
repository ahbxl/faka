package com.card.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.OrderDao;
import com.card.dao.ProductDao;
import com.card.entity.Order;
import com.card.entity.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService extends ServiceImpl<OrderDao, Order> {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;


    public void deleteByState(Integer state) {
        lambdaUpdate().eq(Order::getState, state).remove();
    }

    public IPage<Order> selectPage(OrderVO orderVO) {
        IPage<Order> orderIPage = lambdaQuery()
                .like(!StrUtil.isBlank(orderVO.getSubject()), Order::getSubject, orderVO.getSubject())
                .like(!StrUtil.isBlank(orderVO.getOutTradeNo()), Order::getOutTradeNo, orderVO.getOutTradeNo())
                .eq(null != orderVO.getState(), Order::getState, orderVO.getState())
                .between(null != orderVO.getStartTime() && null != orderVO.getEndTime(), Order::getCreateTime, orderVO.getStartTime(), orderVO.getEndTime())
                .orderByDesc(Order::getCreateTime)
                .page(new Page<>(orderVO.getPageNum(), orderVO.getPageSize()));
        orderIPage.getRecords().forEach(order -> {
            order.setProduct(productDao.selectById(order.getProductId()));
        });
        return orderIPage;
    }

    public List<Order> selectByState(Integer state) {
        return lambdaQuery().eq(Order::getState, state).list();
    }

    public Order selectByOutTradeNo(String outTradeNo) {
        return lambdaQuery().eq(Order::getOutTradeNo, outTradeNo).one();
    }
}