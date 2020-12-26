package com.card.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Data
public class PageVO {
    @NotNull
    private Long pageSize;
    @NotNull
    private Long pageNum;
    @Past
    private Date startTime;
    @Past
    private Date endTime;

    private List<Long> ids;
}