package com.openplatform.log.domain.service;

import com.openplatform.log.domain.command.CreateLogCommand;
import com.openplatform.log.domain.dto.LogDTO;
import com.openplatform.log.domain.entity.Log;

import java.util.List;
import java.util.Optional;

/**
 * 日志服务接口
 */
public interface LogService {
    
    /**
     * 创建日志
     * @param createLogCommand 创建日志命令
     * @return 日志信息
     */
    Log createLog(CreateLogCommand createLogCommand);
    
    /**
     * 根据ID删除日志
     * @param id 日志ID
     */
    void deleteLogById(Long id);
    
    /**
     * 根据ID查找日志
     * @param id 日志ID
     * @return 日志信息
     */
    Optional<Log> findLogById(Long id);
    
    /**
     * 查找所有日志
     * @return 日志列表
     */
    List<Log> findAllLogs();
    
    /**
     * 根据服务名称查找日志
     * @param serviceName 服务名称
     * @return 日志列表
     */
    List<Log> findLogsByServiceName(String serviceName);
    
    /**
     * 根据用户ID查找日志
     * @param userId 用户ID
     * @return 日志列表
     */
    List<Log> findLogsByUserId(Long userId);
    
    /**
     * 根据时间范围查找日志
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 日志列表
     */
    List<Log> findLogsByTimeRange(java.time.LocalDateTime startTime, java.time.LocalDateTime endTime);
}