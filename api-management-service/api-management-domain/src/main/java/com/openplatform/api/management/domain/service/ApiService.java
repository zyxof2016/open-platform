package com.openplatform.api.management.domain.service;

import com.openplatform.api.management.domain.command.CreateApiCommand;
import com.openplatform.api.management.domain.dto.ApiDTO;
import com.openplatform.api.management.domain.entity.Api;

import java.util.List;
import java.util.Optional;

/**
 * API管理服务接口
 */
public interface ApiService {
    
    /**
     * 创建API
     * @param createApiCommand 创建API命令
     * @return API信息
     */
    Api createApi(CreateApiCommand createApiCommand);
    
    /**
     * 更新API
     * @param id API ID
     * @param createApiCommand 更新API命令
     * @return API信息
     */
    Api updateApi(Long id, CreateApiCommand createApiCommand);
    
    /**
     * 根据ID删除API
     * @param id API ID
     */
    void deleteApiById(Long id);
    
    /**
     * 根据ID查找API
     * @param id API ID
     * @return API信息
     */
    Optional<Api> findApiById(Long id);
    
    /**
     * 根据路径和方法查找API
     * @param path API路径
     * @param method 请求方法
     * @return API信息
     */
    Optional<Api> findApiByPathAndMethod(String path, String method);
    
    /**
     * 查找所有API
     * @return API列表
     */
    List<Api> findAllApis();
    
    /**
     * 根据服务名称查找API
     * @param serviceName 服务名称
     * @return API列表
     */
    List<Api> findApisByServiceName(String serviceName);
}