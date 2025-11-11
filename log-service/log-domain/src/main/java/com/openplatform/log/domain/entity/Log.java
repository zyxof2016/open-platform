package com.openplatform.log.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日志实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Log extends BaseEntity {

    private String traceId;
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