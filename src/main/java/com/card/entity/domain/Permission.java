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
@TableName("permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 4551749462814959751L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("name")
    private String name;
    @TableField("description")
    private String description;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

    public ResultVO<Object> validate() {
        if (name == null) {
            return ResultVOUtil.success("name不能为空");
        }
        if (description == null) {
            return ResultVOUtil.success("description不能为空");
        }
        return null;
    }
}