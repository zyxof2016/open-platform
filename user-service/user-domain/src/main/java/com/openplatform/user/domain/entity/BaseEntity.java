package com.openplatform.user.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 基础实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity {
    
    private Long id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String createdBy;
    private String updatedBy;
    private Long version;
    private String tenantId;
}