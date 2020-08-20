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
@TableName("user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1016963557719875417L;
    @TableId("id")
    private Long id;
    @TableField("user_id")
    private Long userId;
    @TableField("role_id")
    private Long roleId;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

    public ResultVO<Object> validate() {
        if (userId == null) {
            return ResultVOUtil.success("userId不能为空");
        }
        if (roleId == null) {
            return ResultVOUtil.success("roleId不能为空");
        }
        return null;
    }
}
