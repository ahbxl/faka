package com.card.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.OrderDao;
import com.card.dao.ProductDao;
import com.card.entity.Order;
import com.card.entity.vo.OrderVO;
import com.card.security.utils.SecurityUtil;
import com.card.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class OrderService extends ServiceImpl<OrderDao, Order> {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserService userService;


    public void deleteByState(Integer state) {
        lambdaUpdate().eq(Order::getState, state).remove();
    }

    public IPage<Order> selectPage(OrderVO orderVO) {
        List<Long> longs = userService.selectUserIds(SecurityUtil.getCurrentUser().getId(), true);
        IPage<Order> orderIPage = lambdaQuery()
                .in(Order::getCreator, longs)
                .like(!StrUtil.isBlank(orderVO.getSubject()), Order::getSubject, orderVO.getSubject())
                .like(!StrUtil.isBlank(orderVO.getOutTradeNo()), Order::getOutTradeNo, orderVO.getOutTradeNo())
                .eq(null != orderVO.getState(), Order::getState, orderVO.getState())
                .between(null != orderVO.getStartTime() && null != orderVO.getEndTime(), Order::getCreateTime, orderVO.getStartTime(), orderVO.getEndTime())
                .eq(Order::getCreator, SecurityUtil.getCurrentUser().getId())
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

    public Map<String, Long> orderCharts(OrderVO orderVO) {
        Map<String, Long> map = new TreeMap<>();
        ArrayList<Date> dayList = DateUtils.getDayList(14, true);
        dayList.forEach(date -> {
            long count = orderDao.selectCount(new QueryWrapper<Order>()
                    .lambda()
                    .between(Order::getCreateTime, DateUtils.getStartDate(date), DateUtils.getEndDate(date))).longValue();
            map.put(DateUtil.format(date, "yyyy-MM-dd"), count);
        });
        return map;
    }

    public Map<String, BigDecimal> priceCharts(OrderVO orderVO) {
        Map<String, BigDecimal> map = new TreeMap<>();
        ArrayList<Date> dayList = DateUtils.getDayList(14, true);
        dayList.forEach(date -> {
            Map<String, Object> map1 = getMap(new QueryWrapper<Order>()
                    .select("IFNULL(sum(total_amount),0) as totalPrice")
                    .between("create_time", DateUtils.getStartDate(date), DateUtils.getEndDate(date)));
            BigDecimal sumCount = (BigDecimal) map1.get("totalPrice");
            map.put(DateUtil.format(date, "yyyy-MM-dd"), sumCount);
        });
        return map;
    }
}