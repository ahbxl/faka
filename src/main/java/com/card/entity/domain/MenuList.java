package com.card.entity.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@TableName("menu_list")
public class MenuList implements Serializable {
    private static final long serialVersionUID = 6371298069578228652L;
    @TableId("id")
    private Long id;
    @TableField("name")
    private String name;
    @TableField("parent")
    private Long parent;
    @TableField("priority")
    private Integer priority;
    @TableField("path")
    private String path;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
}
