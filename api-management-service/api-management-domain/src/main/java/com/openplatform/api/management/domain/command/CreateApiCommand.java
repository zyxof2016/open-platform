package com.openplatform.api.management.domain.command;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 创建API命令
 */
@Data
public class CreateApiCommand {
    
    @NotBlank(message = "API名称不能为空")
    private String name;
    
    @NotBlank(message = "API路径不能为空")
    private String path;
    
    @NotBlank(message = "请求方法不能为空")
    private String method;
    
    private String description;
    
    private String apiVersion;
    
    private String status;
    
    @NotBlank(message = "服务名称不能为空")
    private String serviceName;
    
    private String groupName;
}