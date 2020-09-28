package com.card.controller;

import com.card.command.IdsCommand;
import com.card.command.order.OrderSelectCommand;
import com.card.command.product.ProductFindCommand;
import com.card.entity.domain.Order;
import com.card.entity.domain.Product;
import com.card.entity.vo.ResultVO;
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
}
