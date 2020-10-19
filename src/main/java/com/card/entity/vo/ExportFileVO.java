package com.card.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExportFileVO extends Page implements Serializable {
    private static final long serialVersionUID = 1355891832173560629L;
    private Long id;
    private String name;
    private String path;
    private Long creator;
    private Integer state;
    private Date createTime;
    private Date updateTime;
}