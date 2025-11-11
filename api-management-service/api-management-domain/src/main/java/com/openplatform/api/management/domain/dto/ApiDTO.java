package com.openplatform.api.management.domain.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * API数据传输对象
 */
@Data
public class ApiDTO {
    
    private Long id;
    
    private String name;
    
    private String path;
    
    private String method;
    
    private String description;
    
    private String version;
    
    private String status;
    
    private String serviceName;
    
    private String groupName;
    
    private LocalDateTime createdTime;
    
    private LocalDateTime updatedTime;
}