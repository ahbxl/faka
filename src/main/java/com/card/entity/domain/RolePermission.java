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
@TableName("role_permission")
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -3026205404689502862L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("role_id")
    private Long roleId;
    @TableField("permission_id")
    private Long permissionId;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

    public ResultVO<Object> validate() {
        if (roleId == null) {
            return ResultVOUtil.success("roleId不能为空");
        }
        if (permissionId == null) {
            return ResultVOUtil.success("permissionId不能为空");
        }
        return null;
    }
}
