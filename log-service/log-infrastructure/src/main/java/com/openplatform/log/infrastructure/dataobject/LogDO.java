package com.openplatform.log.infrastructure.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 日志数据对象
 */
@Data
@TableName("op_log")
public class LogDO {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @TableField("created_time")
    private LocalDateTime createdTime;
    
    @TableField("updated_time")
    private LocalDateTime updatedTime;
    
    @Version
    @TableField("version")
    private Long version;
    
    @TableField("tenant_id")
    private String tenantId;
    
    @TableField("trace_id")
    private String traceId;
    
    @TableField("service_name")
    private String serviceName;
    
    @TableField("module_name")
    private String moduleName;
    
    @TableField("operation")
    private String operation;
    
    @TableField("method")
    private String method;
    
    @TableField("url")
    private String url;
    
    @TableField("ip")
    private String ip;
    
    @TableField("user_id")
    private Long userId;
    
    @TableField("username")
    private String username;
    
    @TableField("request_params")
    private String requestParams;
    
    @TableField("response_result")
    private String responseResult;
    
    @TableField("time_consuming")
    private Long timeConsuming;
    
    @TableField("log_type")
    private String logType;
    
    @TableField("log_level")
    private String logLevel;
    
    @TableField("exception_detail")
    private String exceptionDetail;
    
    @TableField("status")
    private String status;
}