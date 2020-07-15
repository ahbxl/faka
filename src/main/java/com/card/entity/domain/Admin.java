package com.card.entity.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName("admin")
public class Admin {
    @TableId("id")
    private Integer id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
}
