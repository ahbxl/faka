package com.card.entity.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Page {
    private Integer pageSize;
    private Integer pageNum;
    private Date createTime;
    private Date updateTime;
    private List<Long> ids;
}