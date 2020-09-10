package com.card.entity.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.card.entity.vo.ResultVO;
import com.card.util.ResultVOUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@TableName("card")
public class Card implements Serializable {
    private static final long serialVersionUID = 8632318572265851654L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("content")
    private String content;
    @TableField("state")
    private Boolean state; // 状态 0/未售出 1/已售出 默认未售出
    @TableField("product_id")
    private Long productId;
    @TableField("create_time")
    private Date createTime;

    public Card doBuild() {
        Card card = new Card();
        card.setId(id);
        card.setContent(content);
        card.setState(state);
        card.setProductId(productId);
        return card;
    }

    public ResultVO<Object> validate() {
        if (content == null) {
            return ResultVOUtil.success("content不能为空");
        }
        if (productId == null) {
            return ResultVOUtil.success("productId不能为空");
        }
        return null;
    }
}