package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.entity.ExportFile;
import com.card.entity.vo.ExportFileVO;

import java.util.List;

public interface ExportFileService {
    /**
     * 分页查询文件列表
     *
     * @param exportFileVO
     * @return
     */
    IPage<ExportFile> selectPage(ExportFileVO exportFileVO);

    /**
     * 保存excel到数据库
     *
     * @param exportFile
     */
    void insert(ExportFile exportFile);

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
    void deleteBatchIds(List<Long> ids);

    ExportFile selectById(Long id);
}