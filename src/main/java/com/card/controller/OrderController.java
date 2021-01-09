package com.card.controller;

import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.card.entity.Order;
import com.card.entity.vo.OrderVO;
import com.card.entity.vo.Result;
import com.card.enu.OrderState;
import com.card.service.AliPayService;
import com.card.service.OrderService;
import com.card.service.UserService;
import com.card.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private UserService userService;

    @PostMapping("/selectPage")
    public Result<Object> selectPage(@RequestBody OrderVO orderVO) {
        return Result.success(orderService.selectPage(orderVO));
    }

    @PostMapping("/selectById")
    public Result<Object> selectById(@RequestBody OrderVO orderVO) {
        return Result.success(orderService.selectById(orderVO.getId()));
    }

    /**
     * 批量删除订单
     * 需要管理员权限
     *
     * @param orderVO
     * @return
     */
    @PostMapping("/deleteBatchIds")
    public Result<Object> deleteBatchIds(@RequestBody OrderVO orderVO) {
        ArrayList<Long> list = new ArrayList<>();
        List<Long> longs = userService.selectIdsByParentId(SecurityUtil.getCurrentUser().getId());
        for (Long id : orderVO.getIds()) {
            Order order = orderService.selectById(id);
            if (order != null && longs.contains(order.getCreator())) {
                list.add(id);
            }
        }
        orderService.deleteBatchIds(list);
        log.info("用户{}删除了订单，订单号为{}", SecurityUtil.getCurrentUser().getId(), list);
        return Result.success();
    }

    @PostMapping("/updateById")
    public Result<Object> updateById(@RequestBody Order order) {
        Order orderById = orderService.selectById(order.getId());
        if (orderById == null) {
            Result.fail("不存在该订单");
        }
        orderService.updateById(order);
        log.info("用户{}更新了订单{}", SecurityUtil.getCurrentUser().getId(), order);
        return Result.success();
    }

    /**
     * 支付宝当面付接口
     *
     * @param order
     * @return
     */
    @PostMapping("/faceToFace")
    public Result<Object> faceToFace(@RequestBody Order order) {
        log.info("当面付支付调用");
        AlipayTradePrecreateResponse response = aliPayService.faceToFace(order.getSubject(), order.getOutTradeNo(), order.getTotalAmount());
        if (ResponseChecker.success(response)) {
            order.setState(OrderState.Pay.getValue());
            order.setCreator(SecurityUtil.getCurrentUser().getId());
            orderService.insert(order);
            log.info("生成支付二维码链接");
            return Result.success(response.qrCode);
        }
        return Result.success("支付调用失败，原因：" + response.msg + "，" + response.subMsg);
    }
}
