package com.card.command.alipay;

import com.card.entity.domain.Order;
import com.card.entity.vo.ResultVO;
import com.card.util.RandomUtil;
import com.card.util.ResultVOUtil;
import lombok.Data;

@Data
public class AliPayCommand {
    // 产品id
    private Integer productId;
    // 产品数量
    private Integer quantity;
    // 主题
    private String subject;
    // 订单号
    private String outTradeNo = RandomUtil.getStringRandom(9); // 随机订单号
    // 总金额
    private String totalAmount;
    // 状态
    private Boolean state = false; // 默认状态为未支付

    public ResultVO<Object> validate() {
        if (productId == null) {
            return ResultVOUtil.success("productId不能为空");
        }
        if (quantity < 1) {
            return ResultVOUtil.success("quantity不能小于1");
        }
        if (subject == null) {
            return ResultVOUtil.success("subject不能为空");
        }
        if (outTradeNo == null) {
            return ResultVOUtil.success("outTradeNo不能为空");
        }
        if (totalAmount == null) {
            return ResultVOUtil.success("totalAmount不能为空");
        }
        return null;
    }

    public Order doBuildOrder() {
        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setSubject(subject);
        order.setOutTradeNo(outTradeNo);
        order.setTotalAmount(totalAmount);
        order.setState(state);
        return order;
    }
}