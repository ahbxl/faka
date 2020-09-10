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
@TableName("category")
public class Category implements Serializable {
    private static final long serialVersionUID = -5888981197198625157L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("name")
    private String name;
    @TableField("parent")
    private Long parent;
    @TableField("state") // 状态 0/下架 1/上架 默认下架
    private Boolean state;
    @TableField("create_time")
    private Date createTime;

    public Category doBuild() {
        Category category = new Category();
        category.setName(name);
        category.setParent(parent);
        category.setState(state);
        return category;
    }

    public ResultVO<Object> validate() {
        if (name == null) {
            return ResultVOUtil.success("name不能为空");
        }
        return null;
    }
}