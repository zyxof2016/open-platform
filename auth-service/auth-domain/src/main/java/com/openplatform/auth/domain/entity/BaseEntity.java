package com.openplatform.auth.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 基础实体类
 */
@Data
@EqualsAndHashCode
public abstract class BaseEntity {

    private Long id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Long version;
    private String tenantId;
}