package com.card.service;

import com.card.entity.ExportFile;

/**
 * 基于Spring创建线程任务服务接口
 */
public interface CustomMultiThreadingService {
    /**
     * 将卡密导出的异步任务
     */
    void executeAysncCardExport(Long startTime, Long endTime, ExportFile exportFile);
}
