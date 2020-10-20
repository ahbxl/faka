package com.card.controller;

import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.card.entity.Order;
import com.card.entity.vo.OrderVO;
import com.card.entity.vo.ResultVO;
import com.card.service.AliPayService;
import com.card.service.OrderService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AliPayService aliPayService;

    @PostMapping("/admin/selectPage")
    public ResultVO<Object> selectPage(@RequestBody OrderVO orderVO) {
        return ResultVOUtil.success(orderService.selectPage(orderVO));
    }

    @PostMapping("/admin/selectOne/{id}")
    public ResultVO<Object> selectOne(@PathVariable("id") Long id) {
        return ResultVOUtil.success(orderService.selectOne(id));
    }

    /**
     * 批量删除订单
     * 需要管理员权限
     *
     * @param orderVO
     * @return
     */
    @PostMapping("/admin/deleteByIds")
    public ResultVO<Object> deleteByIds(@RequestBody OrderVO orderVO) {
        orderService.deleteBatchIds(orderVO.getIds());
        return ResultVOUtil.success();
    }

    @PostMapping("/admin/updateById")
    public ResultVO<Object> updateById(@RequestBody Order order) {
        Order orderById = orderService.selectOne(order.getId());
        if (orderById == null) {
            ResultVOUtil.fail("不存在该订单信息");
        }
        orderService.updateById(order);
        return ResultVOUtil.success();
    }

    /**
     * 支付宝当面付接口
     *
     * @param order
     * @return
     */
    @PostMapping("/faceToFace")
    public ResultVO<Object> faceToFace(@RequestBody Order order) {
        log.info("当面付支付调用");
        AlipayTradePrecreateResponse response = aliPayService.faceToFace(order.getSubject(), order.getOutTradeNo(), order.getTotalAmount());
        if (ResponseChecker.success(response)) {
            orderService.insert(order);
            log.info("生成支付二维码链接");
            return ResultVOUtil.success(response.qrCode);
        }
        return ResultVOUtil.success("支付调用失败，原因：" + response.msg + "，" + response.subMsg);
    }
}
