package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.card.command.exportfile.ExportFileCommand;
import com.card.dao.ExportFileDao;
import com.card.entity.domain.ExportFile;
import com.card.enu.ExportFileState;
import com.card.service.ExportFileService;
import com.card.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
public class ExportFileServiceImpl implements ExportFileService {
    @Autowired
    private ExportFileDao exportFileDao;

    @Autowired
    private HttpServletResponse response;

    @Override
    public IPage<ExportFile> findByPage(Integer pageNum, Integer pageSize, ExportFileCommand command) {
        Page<ExportFile> productPage = new Page<>(pageNum, pageSize);
        QueryWrapper<ExportFile> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(command.getFileName())) {
            wrapper.like("file_name", command.getFileName());
        }
        if (command.getStartTime() != null && command.getEndTime() != null) {
            wrapper.between("create_time", command.getStartTime(), command.getEndTime());
        }
        return exportFileDao.selectPage(productPage, wrapper);
    }

    @Override
    public ExportFile saveExportFile(String fileName, String path, Integer state) {
        ExportFile exportFile = new ExportFile();
        exportFile.setFileName(fileName);
        exportFile.setPath(path);
        exportFile.setCreator(SecurityUtil.getCurrentUser().getId());
        exportFile.setState(state);
        exportFileDao.insert(exportFile);
        return exportFile;
    }

    @Override
    public void downloadExportFile(ExportFile exportFile) {
        try {
            File file = new File(exportFile.getPath());
            String fileName = file.getName();
            FileInputStream fis = new FileInputStream(exportFile.getPath());
            // 设置头部信息
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes(), StandardCharsets.ISO_8859_1));
            response.setCharacterEncoding("UTF-8");
            OutputStream os = response.getOutputStream();
            FileCopyUtils.copy(fis, os);
        } catch (IOException e) {
            log.error("文件内容读取异常，文件:" + e.getMessage());
        } finally {
            // 修改数据库中的文件状态为已下载
            UpdateWrapper<ExportFile> wrapper = new UpdateWrapper<>();
            wrapper.set("state", ExportFileState.Downloaded.getValue());
            exportFileDao.update(exportFile, wrapper);
        }
    }

    @Override
    public void deleteExportFile(List<Long> ids) {
        exportFileDao.deleteByIds(ids);

    }

    @Override
    public ExportFile selectById(Long id) {
        return exportFileDao.selectById(id);
    }
}