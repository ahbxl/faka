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
@TableName("admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = -7912163658901708119L;
    @TableId("id")
    private Long id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;

    public ResultVO<Object> validate() {
        if (username == null) {
            return ResultVOUtil.success("username不能为空");
        }
        if (password == null) {
            return ResultVOUtil.success("password不能为空");
        }
        return null;
    }
}