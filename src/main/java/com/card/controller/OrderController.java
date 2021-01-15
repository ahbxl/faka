package com.card.controller;

import cn.hutool.core.util.StrUtil;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.card.entity.Order;
import com.card.entity.vo.OrderVO;
import com.card.entity.vo.Result;
import com.card.enu.OrderState;
import com.card.security.utils.SecurityUtil;
import com.card.service.AliPayService;
import com.card.service.OrderService;
import com.card.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AliPayService aliPayService;
    @Autowired
    private ProductService productService;

    @PostMapping("/selectPage")
    public Result<Object> selectPage(@RequestBody OrderVO orderVO) {
        return Result.success(orderService.selectPage(orderVO));
    }

    @PostMapping("/getById")
    public Result<Object> getById(@RequestBody OrderVO orderVO) {
        Order order = orderService.getById(orderVO.getId());
        if (order != null) order.setProduct(productService.getById(order.getProductId()));
        return Result.success(order);
    }

    /**
     * 批量删除订单
     * 需要管理员权限
     *
     * @param orderVO
     * @return
     */
    @PostMapping("/removeById")
    @RequiresPermissions({"order:delete"})
    public Result<Object> removeById(@RequestBody OrderVO orderVO) {
        Order order = orderService.getById(orderVO.getId());
        if (order != null && SecurityUtil.getCurrentUser().getId().equals(order.getCreator())) {
            orderService.removeById(orderVO.getId());
            log.info("用户{}删除了订单，订单id为{}", SecurityUtil.getCurrentUser().getId(), orderVO.getId());
        }
        return Result.success();
    }

    @PostMapping("/saveOrUpdate")
    @RequiresPermissions({"order:update"})
    public Result<Object> saveOrUpdate(@RequestBody Order order) {
        Integer count = orderService.lambdaQuery()
                .eq(StrUtil.isNotBlank(order.getSubject()), Order::getSubject, order.getSubject())
                .ne(order.getId() != null, Order::getId, order.getId())
                .count();
        if (count > 0) return Result.fail("订单标题或者订单号已存在");
        orderService.lambdaUpdate()
                .set(StrUtil.isNotBlank(order.getSubject()), Order::getSubject, order.getSubject())
                .set(order.getState() != null, Order::getState, order.getState())
                .update();
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
            orderService.saveOrUpdate(order);
            log.info("生成支付二维码链接");
            return Result.success(response.qrCode);
        }
        return Result.success("支付调用失败，原因：" + response.msg + "，" + response.subMsg);
    }
}
