package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.command.exportfile.ExportFileCommand;
import com.card.entity.ExportFile;

import java.util.List;

public interface ExportFileService {
    /**
     * 分页查询文件列表
     *
     * @param pageNum
     * @param pageSize
     * @param command
     * @return
     */
    IPage<ExportFile> findByPage(Integer pageNum, Integer pageSize, ExportFileCommand command);

    /**
     * 保存excel到数据库
     *
     * @param fileName 文件名称
     * @param path     保存路径
     * @param state    状态
     * @return ExportFile 文件对象
     */
    ExportFile saveExportFile(String fileName, String path, Integer state);

    /**
     * 下载文件
     *
     * @param exportFile 需要下载的文件对象
     */
    void downloadExportFile(ExportFile exportFile);

    /**
     * 删除文件
     *
     * @param ids 文件的主键
     */
    void deleteExportFile(List<Long> ids);

    ExportFile selectById(Long id);
}