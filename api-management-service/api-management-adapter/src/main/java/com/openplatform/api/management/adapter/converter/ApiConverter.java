package com.openplatform.api.management.adapter.converter;

/**
 * API转换器
 */
public class ApiConverter {
    
    /**
     * 将领域层API实体转换为应用层API DTO
     * @param domainApi 领域层API实体
     * @return 应用层API DTO
     */
    public static com.openplatform.api.management.app.dto.ApiDTO toApp(com.openplatform.api.management.domain.entity.Api domainApi) {
        com.openplatform.api.management.app.dto.ApiDTO appDto = new com.openplatform.api.management.app.dto.ApiDTO();
        appDto.setId(domainApi.getId());
        appDto.setName(domainApi.getName());
        appDto.setPath(domainApi.getPath());
        appDto.setMethod(domainApi.getMethod());
        appDto.setDescription(domainApi.getDescription());
        appDto.setVersion(domainApi.getApiVersion());
        appDto.setStatus(domainApi.getStatus());
        appDto.setServiceName(domainApi.getServiceName());
        appDto.setGroupName(domainApi.getGroupName());
        appDto.setCreatedTime(domainApi.getCreatedTime());
        appDto.setUpdatedTime(domainApi.getUpdatedTime());
        return appDto;
    }
    
    /**
     * 将应用层创建API命令转换为领域层创建API命令
     * @param appCommand 应用层创建API命令
     * @return 领域层创建API命令
     */
    public static com.openplatform.api.management.domain.command.CreateApiCommand toDomain(com.openplatform.api.management.app.command.CreateApiCommand appCommand) {
        com.openplatform.api.management.domain.command.CreateApiCommand domainCommand = new com.openplatform.api.management.domain.command.CreateApiCommand();
        domainCommand.setName(appCommand.getName());
        domainCommand.setPath(appCommand.getPath());
        domainCommand.setMethod(appCommand.getMethod());
        domainCommand.setDescription(appCommand.getDescription());
        domainCommand.setApiVersion(appCommand.getVersion());
        domainCommand.setStatus(appCommand.getStatus());
        domainCommand.setServiceName(appCommand.getServiceName());
        domainCommand.setGroupName(appCommand.getGroupName());
        return domainCommand;
    }
}