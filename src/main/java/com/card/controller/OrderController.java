package com.card.controller;

import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.card.entity.Order;
import com.card.entity.vo.OrderVO;
import com.card.entity.vo.ResultVO;
import com.card.enu.OrderState;
import com.card.service.AliPayService;
import com.card.service.OrderService;
import com.card.service.UserService;
import com.card.util.ResultVOUtil;
import com.card.util.SecurityUtil;
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
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private UserService userService;

    @PostMapping("/token/selectPage")
    public ResultVO<Object> selectPage(@RequestBody OrderVO orderVO) {
        return ResultVOUtil.success(orderService.selectPage(orderVO));
    }

    @PostMapping("/token/selectById")
    public ResultVO<Object> selectById(@RequestBody OrderVO orderVO) {
        return ResultVOUtil.success(orderService.selectById(orderVO.getId()));
    }

    /**
     * 批量删除订单
     * 需要管理员权限
     *
     * @param orderVO
     * @return
     */
    @PostMapping("/token/deleteBatchIds")
    public ResultVO<Object> deleteBatchIds(@RequestBody OrderVO orderVO) {
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
        return ResultVOUtil.success();
    }

    @PostMapping("/token/updateById")
    public ResultVO<Object> updateById(@RequestBody Order order) {
        Order orderById = orderService.selectById(order.getId());
        if (orderById == null) {
            ResultVOUtil.fail("不存在该订单");
        }
        orderService.updateById(order);
        log.info("用户{}更新了订单{}", SecurityUtil.getCurrentUser().getId(), order);
        return ResultVOUtil.success();
    }

    /**
     * 支付宝当面付接口
     *
     * @param order
     * @return
     */
    @PostMapping("/token/faceToFace")
    public ResultVO<Object> faceToFace(@RequestBody Order order) {
        log.info("当面付支付调用");
        AlipayTradePrecreateResponse response = aliPayService.faceToFace(order.getSubject(), order.getOutTradeNo(), order.getTotalAmount());
        if (ResponseChecker.success(response)) {
            order.setState(OrderState.Pay.getValue());
            order.setCreator(SecurityUtil.getCurrentUser().getId());
            orderService.insert(order);
            log.info("生成支付二维码链接");
            return ResultVOUtil.success(response.qrCode);
        }
        return ResultVOUtil.success("支付调用失败，原因：" + response.msg + "，" + response.subMsg);
    }
}
