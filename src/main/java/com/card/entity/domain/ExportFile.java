package com.card.entity.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("export_file")
public class ExportFile implements Serializable {
    private static final long serialVersionUID = -4354740234512411573L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("file_name")
    private String fileName;
    @TableField("path")
    private String path;
    @TableField("creator")
    private Long creator;
    @TableField("state")
    private Integer state;
    @TableField("create_time")
    private Date createTime;
}