package com.openplatform.log.adapter.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.openplatform.log.adapter.converter.LogConverter;
import com.openplatform.log.app.command.CreateLogCommand;
import com.openplatform.log.app.dto.LogDTO;
import com.openplatform.log.domain.entity.Log;
import com.openplatform.log.domain.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 日志管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/logs")
@RequiredArgsConstructor
public class LogController {
    
    private final LogService logService;
    
    /**
     * 创建日志
     * @param createLogCommand 创建日志命令
     * @return 日志信息
     */
    @PostMapping
    public SingleResponse<LogDTO> createLog(@Valid @RequestBody CreateLogCommand createLogCommand) {
        log.info("创建日志请求: {} - {}", createLogCommand.getServiceName(), createLogCommand.getOperation());
        
        try {
            com.openplatform.log.domain.command.CreateLogCommand domainCommand = 
                LogConverter.toDomain(createLogCommand);
            Log logEntity = logService.createLog(domainCommand);
            LogDTO logDTO = LogConverter.toApp(logEntity);
            return SingleResponse.of(logDTO);
        } catch (Exception e) {
            log.error("日志创建失败", e);
            return SingleResponse.buildFailure("CREATE_LOG_ERROR", "日志创建失败: " + e.getMessage());
        }
    }
    
    /**
     * 根据ID获取日志
     * @param id 日志ID
     * @return 日志信息
     */
    @GetMapping("/{id}")
    public SingleResponse<LogDTO> getLogById(@PathVariable Long id) {
        log.info("获取日志请求: {}", id);
        
        return logService.findLogById(id)
                .map(log -> SingleResponse.of(LogConverter.toApp(log)))
                .orElse(SingleResponse.buildFailure("NOT_FOUND", "日志不存在"));
    }
    
    /**
     * 获取所有日志
     * @return 日志列表
     */
    @GetMapping
    public MultiResponse<LogDTO> getAllLogs() {
        log.info("获取所有日志请求");
        
        List<Log> logs = logService.findAllLogs();
        List<LogDTO> logDTOs = logs.stream()
                .map(LogConverter::toApp)
                .collect(Collectors.toList());
        
        return MultiResponse.of(logDTOs);
    }
    
    /**
     * 根据服务名称获取日志
     * @param serviceName 服务名称
     * @return 日志列表
     */
    @GetMapping("/service/{serviceName}")
    public MultiResponse<LogDTO> getLogsByServiceName(@PathVariable String serviceName) {
        log.info("根据服务名称获取日志请求: {}", serviceName);
        
        List<Log> logs = logService.findLogsByServiceName(serviceName);
        List<LogDTO> logDTOs = logs.stream()
                .map(LogConverter::toApp)
                .collect(Collectors.toList());
        
        return MultiResponse.of(logDTOs);
    }
    
    /**
     * 根据用户ID获取日志
     * @param userId 用户ID
     * @return 日志列表
     */
    @GetMapping("/user/{userId}")
    public MultiResponse<LogDTO> getLogsByUserId(@PathVariable Long userId) {
        log.info("根据用户ID获取日志请求: {}", userId);
        
        List<Log> logs = logService.findLogsByUserId(userId);
        List<LogDTO> logDTOs = logs.stream()
                .map(LogConverter::toApp)
                .collect(Collectors.toList());
        
        return MultiResponse.of(logDTOs);
    }
    
    /**
     * 根据时间范围获取日志
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 日志列表
     */
    @GetMapping("/time-range")
    public MultiResponse<LogDTO> getLogsByTimeRange(
            @RequestParam String startTime,
            @RequestParam String endTime) {
        log.info("根据时间范围获取日志请求: {} - {}", startTime, endTime);
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime start = LocalDateTime.parse(startTime, formatter);
            LocalDateTime end = LocalDateTime.parse(endTime, formatter);
            
            List<Log> logs = logService.findLogsByTimeRange(start, end);
            List<LogDTO> logDTOs = logs.stream()
                    .map(LogConverter::toApp)
                    .collect(Collectors.toList());
            
            return MultiResponse.of(logDTOs);
        } catch (Exception e) {
            log.error("时间范围查询失败", e);
            return MultiResponse.buildFailure("QUERY_ERROR", "时间范围查询失败: " + e.getMessage());
        }
    }
    
    /**
     * 删除日志
     * @param id 日志ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public Response deleteLog(@PathVariable Long id) {
        log.info("删除日志请求: {}", id);
        
        try {
            logService.deleteLogById(id);
            return Response.buildSuccess();
        } catch (Exception e) {
            log.error("日志删除失败", e);
            return Response.buildFailure("DELETE_LOG_ERROR", "日志删除失败: " + e.getMessage());
        }
    }
}