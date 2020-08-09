package com.card.entity.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.card.entity.vo.ResultVO;
import com.card.util.ResultVOUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName("user")
public class User {
    @TableId("id")
    private Long id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("email")
    private String email;
    @TableField("state")
    private Boolean state;

    public ResultVO<Object> validate() {
        if (username == null) {
            return ResultVOUtil.success("username不能为空");
        }
        if (password == null) {
            return ResultVOUtil.success("password不能为空");
        }
        if (email == null) {
            return ResultVOUtil.success("email不能为空");
        }
        if (state == null) {
            return ResultVOUtil.success("state不能为空");
        }
        return null;
    }
}
