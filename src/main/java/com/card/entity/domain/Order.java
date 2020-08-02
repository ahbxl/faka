package com.card.entity.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.card.entity.vo.ResultVO;
import com.card.util.ResultVOUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@TableName("order")
public class Order implements Serializable {
    private static final long serialVersionUID = -6692823745412833806L;
    @TableId("id")
    private Integer id;
    @TableField("product_id")
    private Integer productId;
    @TableField("quantity")
    private Integer quantity;
    @TableField("subject")
    private String subject;
    @TableField("outTradeNo")
    private String outTradeNo;
    @TableField("total_amount")
    private String totalAmount;
    @TableField("state")
    private Integer state;
    @TableField("create_time")
    private Long createTime;

    public Order doBuild() {
        Order order = new Order();
        order.setId(id);
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setSubject(subject);
        order.setOutTradeNo(outTradeNo);
        order.setTotalAmount(totalAmount);
        order.setState(0);
        order.setCreateTime(System.currentTimeMillis());
        return order;
    }

    public ResultVO<Object> validate() {
        if (productId == null) {
            return ResultVOUtil.success("productId不能为空");
        }
        if (subject == null) {
            return ResultVOUtil.success("subject不能为空");
        }
        if (quantity < 1) {
            return ResultVOUtil.success("quantity不能小于1");
        }
        if (outTradeNo == null) {
            return ResultVOUtil.success("outTradeNo不能为空");
        }
        if (totalAmount == null) {
            return ResultVOUtil.success("totalAmount不能为空");
        }
        return null;
    }
}