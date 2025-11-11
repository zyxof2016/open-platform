package com.openplatform.log.app.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 日志响应DTO
 */
@Data
public class LogDTO {
    
    private Long id;
    
    private String traceId;
    
    private String serviceName;
    
    private String moduleName;
    
    private String operation;
    
    private String method;
    
    private String url;
    
    private String ip;
    
    private Long userId;
    
    private String username;
    
    private String requestParams;
    
    private String responseResult;
    
    private Long timeConsuming;
    
    private String logType;
    
    private String logLevel;
    
    private String exceptionDetail;
    
    private String status;
    
    private LocalDateTime createdTime;
    
    private LocalDateTime updatedTime;
    
    private String createdBy;
    
    private String updatedBy;
}