package com.openplatform.log.app.command;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * 创建日志命令
 */
@Data
public class CreateLogCommand {
    
    private String traceId;
    
    @NotBlank(message = "服务名称不能为空")
    private String serviceName;
    
    private String moduleName;
    
    @NotBlank(message = "操作不能为空")
    private String operation;
    
    @Pattern(regexp = "GET|POST|PUT|DELETE|PATCH", message = "请求方法必须是GET, POST, PUT, DELETE, PATCH之一")
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