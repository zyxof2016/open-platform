package com.openplatform.log.domain.command;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 创建日志命令
 */
@Data
public class CreateLogCommand {
    
    private String traceId;
    
    @NotBlank(message = "服务名称不能为空")
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
}