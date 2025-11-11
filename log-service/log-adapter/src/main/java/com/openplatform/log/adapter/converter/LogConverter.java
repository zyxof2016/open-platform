package com.openplatform.log.adapter.converter;

/**
 * 日志转换器
 */
public class LogConverter {
    
    /**
     * 将领域层日志实体转换为应用层日志DTO
     * @param domainLog 领域层日志实体
     * @return 应用层日志DTO
     */
    public static com.openplatform.log.app.dto.LogDTO toApp(com.openplatform.log.domain.entity.Log domainLog) {
        com.openplatform.log.app.dto.LogDTO appDto = new com.openplatform.log.app.dto.LogDTO();
        appDto.setId(domainLog.getId());
        appDto.setTraceId(domainLog.getTraceId());
        appDto.setServiceName(domainLog.getServiceName());
        appDto.setModuleName(domainLog.getModuleName());
        appDto.setOperation(domainLog.getOperation());
        appDto.setMethod(domainLog.getMethod());
        appDto.setUrl(domainLog.getUrl());
        appDto.setIp(domainLog.getIp());
        appDto.setUserId(domainLog.getUserId());
        appDto.setUsername(domainLog.getUsername());
        appDto.setRequestParams(domainLog.getRequestParams());
        appDto.setResponseResult(domainLog.getResponseResult());
        appDto.setTimeConsuming(domainLog.getTimeConsuming());
        appDto.setLogType(domainLog.getLogType());
        appDto.setLogLevel(domainLog.getLogLevel());
        appDto.setExceptionDetail(domainLog.getExceptionDetail());
        appDto.setStatus(domainLog.getStatus());
        appDto.setCreatedTime(domainLog.getCreatedTime());
        appDto.setUpdatedTime(domainLog.getUpdatedTime());
        return appDto;
    }
    
    /**
     * 将应用层创建日志命令转换为领域层创建日志命令
     * @param appCommand 应用层创建日志命令
     * @return 领域层创建日志命令
     */
    public static com.openplatform.log.domain.command.CreateLogCommand toDomain(com.openplatform.log.app.command.CreateLogCommand appCommand) {
        com.openplatform.log.domain.command.CreateLogCommand domainCommand = new com.openplatform.log.domain.command.CreateLogCommand();
        domainCommand.setTraceId(appCommand.getTraceId());
        domainCommand.setServiceName(appCommand.getServiceName());
        domainCommand.setModuleName(appCommand.getModuleName());
        domainCommand.setOperation(appCommand.getOperation());
        domainCommand.setMethod(appCommand.getMethod());
        domainCommand.setUrl(appCommand.getUrl());
        domainCommand.setIp(appCommand.getIp());
        domainCommand.setUserId(appCommand.getUserId());
        domainCommand.setUsername(appCommand.getUsername());
        domainCommand.setRequestParams(appCommand.getRequestParams());
        domainCommand.setResponseResult(appCommand.getResponseResult());
        domainCommand.setTimeConsuming(appCommand.getTimeConsuming());
        domainCommand.setLogType(appCommand.getLogType());
        domainCommand.setLogLevel(appCommand.getLogLevel());
        domainCommand.setExceptionDetail(appCommand.getExceptionDetail());
        domainCommand.setStatus(appCommand.getStatus());
        return domainCommand;
    }
}