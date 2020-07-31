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
@TableName("category")
public class Category implements Serializable {
    private static final long serialVersionUID = -5888981197198625157L;
    @TableId("id")
    private Long id;
    @TableField("name")
    private String name;
    @TableField("parent")
    private Long parent;
    @TableField("state")
    private Integer state;
    @TableField("create_time")
    private Long createTime;

    public Category doBuild() {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setParent(parent);
        category.setState(state);
        category.setCreateTime(System.currentTimeMillis());
        return category;
    }

    public ResultVO<Object> validate() {
        if (name == null) {
            return ResultVOUtil.success("name不能为空");
        }
        if (state == null) {
            return ResultVOUtil.success("state不能为空");
        }
        return null;
    }
}