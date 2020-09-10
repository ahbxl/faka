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
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@TableName("product")
public class Product implements Serializable {
    private static final long serialVersionUID = -54169886181194401L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("name")
    private String name;
    @TableField("price")
    private BigDecimal price;
    @TableField("state")
    private Boolean state;
    @TableField("category_id")
    private Long categoryId;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;


    public Product doBuild() {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setState(state);
        product.setCategoryId(categoryId);
        return product;
    }

    public ResultVO<Object> validate() {
        if (name == null) {
            return ResultVOUtil.success("name不能为空");
        }
        if (price == null) {
            return ResultVOUtil.success("price不能为空");
        }
        if (categoryId == null) {
            return ResultVOUtil.success("categoryId不能为空");
        }
        return null;
    }
}