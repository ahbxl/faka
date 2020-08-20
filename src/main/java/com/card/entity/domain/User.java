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
import java.util.Date;

@Getter
@Setter
@ToString
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1192800251115892576L;
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
    @TableField("grade")
    private Integer grade; // 用户等级 0/普通用户 1/一级代理 2/二级代理 3/三级代理 默认普通用户
    @TableField("role_id")
    private Long roleId; // 角色 0/普通用户 1/管理员 默认普通用户
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
        return null;
    }
}
