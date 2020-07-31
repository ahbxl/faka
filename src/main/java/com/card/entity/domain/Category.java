package com.card.entity.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.card.command.category.CategoryCommand;
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

    public ResultVO<Object> validate() {
        if (id == null) {
            return ResultVOUtil.success("id不能为空");
        }
        if (name == null) {
            return ResultVOUtil.success("name不能为空");
        }
        if (state == null) {
            return ResultVOUtil.success("state不能为空");
        }
        return null;
    }

    public CategoryCommand doBuild(CategoryCommand categoryCommand) {
        categoryCommand.setId(id);
        categoryCommand.setName(name);
        categoryCommand.setParent(parent);
        categoryCommand.setState(state);
        categoryCommand.setCreateTime(System.currentTimeMillis());
        return categoryCommand;
    }
}