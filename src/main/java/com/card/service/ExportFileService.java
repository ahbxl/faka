package com.card.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.card.command.exportfile.ExportFileCommand;
import com.card.entity.domain.ExportFile;

public interface ExportFileService {
    IPage<ExportFile> findByPage(Integer pageNum, Integer pageSize, ExportFileCommand command);
}
