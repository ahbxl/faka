package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.CardDao;
import com.card.dao.ExportFileDao;
import com.card.entity.Card;
import com.card.entity.ExportFile;
import com.card.entity.vo.ExportFileVO;
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
public class ExportFileServiceImpl extends ServiceImpl<ExportFileDao, ExportFile> implements ExportFileService {
    @Autowired
    private ExportFileDao exportFileDao;

    @Autowired
    private HttpServletResponse response;

    @Override
    public IPage<ExportFile> selectPage(ExportFileVO exportFileVO) {
        Page<ExportFile> productPage = new Page<>(exportFileVO.getPageNum(), exportFileVO.getPageSize());
        QueryWrapper<ExportFile> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(exportFileVO.getName())) {
            wrapper.like("name", exportFileVO.getName());
        }
        if (null != exportFileVO.getCreator()) {
            wrapper.eq("creator", exportFileVO.getCreator());
        }
        if (null != exportFileVO.getState()) {
            wrapper.eq("state", exportFileVO.getState());
        }
        if (null != exportFileVO.getStartTime() && null != exportFileVO.getEndTime()) {
            wrapper.between("create_time", exportFileVO.getStartTime(), exportFileVO.getEndTime());
        }
        wrapper.orderByDesc("create_time");
        return exportFileDao.selectPage(productPage, wrapper);
    }

    @Override
    public Integer insert(ExportFile exportFile) {
        exportFile.setCreator(SecurityUtil.getCurrentUser().getId());
        return exportFileDao.insert(exportFile);
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
    public void deleteBatchIds(List<Long> ids) {
        exportFileDao.deleteBatchIds(ids);
    }

    @Override
    public ExportFile selectById(Long id) {
        return exportFileDao.selectById(id);
    }
}