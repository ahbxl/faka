package com.card.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -4528470984796447897L;
    private Long id;
    private Long roleId;
    private Long permissionId;
    private Integer state;
    private Date createTime;
    private Date updateTime;
}
