package com.card.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.card.command.export.CardExport;
import com.card.dao.CardDao;
import com.card.dao.ExportFileDao;
import com.card.entity.domain.ExportFile;
import com.card.enu.ExportFileState;
import com.card.service.CustomMultiThreadingService;
import com.card.service.ExportFileService;
import com.card.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 基于Spring创建线程任务服务实现类
 */
@Service
@Slf4j
public class CustomMultiThreadingServiceImpl implements CustomMultiThreadingService {
    @Value("${}")
    @Autowired
    private CardDao cardDao;

    @Autowired
    private ExportFileDao exportFileDao;

    /**
     * 通过@Async注解表明该方法是一个异步方法
     * 如果注解在类级别上，则表明该类所有的方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
     */
    @Override
    @Async
    public void executeAysncCardExport(Long startTime, Long endTime, ExportFile exportFile) {
        // sheet1 数据
        List<CardExport> cardExportsZero = cardDao.cardExportFindByStateAndTime(0, startTime, endTime);
        // sheet2 数据
        List<CardExport> cardExportsOne = cardDao.cardExportFindByStateAndTime(1, startTime, endTime);
        // sheet1
        Map<String, Object> zeroSheet = ExcelUtil.createOneSheet("未售出的卡密", "未售出的卡密", CardExport.class, cardExportsZero);
        // sheet2
        Map<String, Object> oneSheet = ExcelUtil.createOneSheet("已经售出的卡密", "未售出的卡密", CardExport.class, cardExportsOne);
        // 导出多个sheet
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(zeroSheet);
        mapList.add(oneSheet);
        Workbook workbook = ExcelUtil.mutiSheet(mapList);
        // 输出文件到服务器/data/faka/exportFile文件夹下


        // 修改数据库中的文件状态为未下载
        UpdateWrapper<ExportFile> wrapper = new UpdateWrapper<>();
        wrapper.set("state", ExportFileState.Not_Downloaded.getValue());
        exportFileDao.update(exportFile, wrapper);
    }
}