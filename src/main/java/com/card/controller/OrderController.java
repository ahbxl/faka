package com.card.controller;

import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.card.command.IdsCommand;
import com.card.command.order.OrderSelectCommand;
import com.card.entity.Order;
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

    @PostMapping("/admin/selectByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> selectByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody OrderSelectCommand command) {
        return ResultVOUtil.success(orderService.selectByPage(pageNum, pageSize, command));
    }

    @PostMapping("/admin/selectOne/{id}")
    public ResultVO<Object> selectOne(@PathVariable("id") Long id) {
        return ResultVOUtil.success(orderService.selectOne(id));
    }

    /**
     * 批量删除订单
     * 需要管理员权限
     *
     * @param command ids
     * @return
     */
    @PostMapping("/admin/deleteByIds")
    public ResultVO<Object> deleteByIds(@RequestBody IdsCommand command) {
        command.validate();
        orderService.deleteByIds(command);
        return ResultVOUtil.success();
    }

    @PostMapping("/admin/updateById/{id}")
    public ResultVO<Object> updateById(@PathVariable("id") Long id, @RequestBody Order order) {
        Order orderById = orderService.selectOne(id);
        if (orderById == null) {
            ResultVOUtil.fail("不存在该订单信息");
        }
        orderService.updateById(id, order);
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
