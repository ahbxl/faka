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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1192800251115892576L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("username")
    @NotBlank(message = "{user.name.notBlank}")
    private String username;
    @NotBlank(message = "{user.password.notBlank}")
    @TableField("password")
    private String password;
    @NotBlank(message = "{user.email.notBlank}")
    @Email(message = "{user.email.pattern}")
    @TableField("email")
    private String email;
    @TableField("state")
    private Boolean state; // 状态 0/禁止 1/正常
    @TableField("role_id")
    private Long roleId; // 角色id
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

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
        if (roleId == null) {
            return ResultVOUtil.success("roleId不能为空");
        }
        return null;
    }

    public User doBuild() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setState(state);
        return user;
    }
}
