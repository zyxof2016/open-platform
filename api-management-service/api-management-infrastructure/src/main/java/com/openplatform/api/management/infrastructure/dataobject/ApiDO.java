package com.openplatform.api.management.infrastructure.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * API数据对象
 */
@Data
@TableName("op_api")
public class ApiDO {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @TableField("created_time")
    private LocalDateTime createdTime;
    
    @TableField("updated_time")
    private LocalDateTime updatedTime;
    
    @Version
    @TableField("version")
    private Long version;
    
    @TableField("name")
    private String name;
    
    @TableField("path")
    private String path;
    
    @TableField("method")
    private String method;
    
    @TableField("description")
    private String description;
    
    @TableField("api_version")
    private String apiVersion;
    
    @TableField("status")
    private String status;
    
    @TableField("service_name")
    private String serviceName;
    
    @TableField("group_name")
    private String groupName;
}