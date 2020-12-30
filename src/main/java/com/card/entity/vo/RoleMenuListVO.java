package com.card.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleMenuListVO extends PageVO implements Serializable {
    private static final long serialVersionUID = 2310103009618403876L;
    private Long id;
    private Long roleId;
    private Long MenuListId;
    private Integer state;
    private Date startTime;
    private Date endTime;
}
