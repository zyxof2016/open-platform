package com.openplatform.api.management.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * API实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Api extends BaseEntity {

    private String name;
    private String path;
    private String method;
    private String description;
    private String apiVersion;
    private String status;
    private String serviceName;
    private String groupName;
}