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
@TableName("export_file")
public class ExportFile {
    @TableId("id")
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
    private Long createTime = System.currentTimeMillis();

}