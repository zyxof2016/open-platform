package com.openplatform.api.management.app.command;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

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
    @Pattern(regexp = "GET|POST|PUT|DELETE|PATCH", message = "请求方法必须是GET, POST, PUT, DELETE, PATCH之一")
    private String method;
    
    private String description;
    
    private String version;
    
    private String status;
    
    @NotBlank(message = "服务名称不能为空")
    private String serviceName;
    
    private String groupName;
}