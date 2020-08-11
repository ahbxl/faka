package com.card.service;

import javax.servlet.http.HttpServletResponse;

/**
 * 基于Spring创建线程任务服务接口
 */
public interface CustomMultiThreadingService {
    /**
     * 将卡密导出的异步任务
     */
    public void executeAysncCardExport(Long startTime, Long endTime);
}
