package com.openplatform.api.management.adapter.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.openplatform.api.management.adapter.converter.ApiConverter;
import com.openplatform.api.management.app.command.CreateApiCommand;
import com.openplatform.api.management.app.dto.ApiDTO;
import com.openplatform.api.management.domain.entity.Api;
import com.openplatform.api.management.domain.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * API管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/apis")
@RequiredArgsConstructor
public class ApiController {
    
    private final ApiService apiService;
    
    /**
     * 创建API
     * @param createApiCommand 创建API命令
     * @return API信息
     */
    @PostMapping
    public SingleResponse<ApiDTO> createApi(@Valid @RequestBody CreateApiCommand createApiCommand) {
        log.info("创建API请求: {} - {} {}", createApiCommand.getName(), createApiCommand.getMethod(), createApiCommand.getPath());
        
        try {
            com.openplatform.api.management.domain.command.CreateApiCommand domainCommand = 
                ApiConverter.toDomain(createApiCommand);
            Api api = apiService.createApi(domainCommand);
            ApiDTO apiDTO = ApiConverter.toApp(api);
            return SingleResponse.of(apiDTO);
        } catch (Exception e) {
            log.error("API创建失败", e);
            return SingleResponse.buildFailure("CREATE_API_ERROR", "API创建失败: " + e.getMessage());
        }
    }
    
    /**
     * 根据ID获取API
     * @param id API ID
     * @return API信息
     */
    @GetMapping("/{id}")
    public SingleResponse<ApiDTO> getApiById(@PathVariable Long id) {
        log.info("获取API请求: {}", id);
        
        return apiService.findApiById(id)
                .map(api -> SingleResponse.of(ApiConverter.toApp(api)))
                .orElse(SingleResponse.buildFailure("NOT_FOUND", "API不存在"));
    }
    
    /**
     * 获取所有API
     * @return API列表
     */
    @GetMapping
    public MultiResponse<ApiDTO> getAllApis() {
        log.info("获取所有API请求");
        
        List<Api> apis = apiService.findAllApis();
        List<ApiDTO> apiDTOs = apis.stream()
                .map(ApiConverter::toApp)
                .collect(Collectors.toList());
        
        return MultiResponse.of(apiDTOs);
    }
    
    /**
     * 根据服务名称获取API
     * @param serviceName 服务名称
     * @return API列表
     */
    @GetMapping("/service/{serviceName}")
    public MultiResponse<ApiDTO> getApisByServiceName(@PathVariable String serviceName) {
        log.info("根据服务名称获取API请求: {}", serviceName);
        
        List<Api> apis = apiService.findApisByServiceName(serviceName);
        List<ApiDTO> apiDTOs = apis.stream()
                .map(ApiConverter::toApp)
                .collect(Collectors.toList());
        
        return MultiResponse.of(apiDTOs);
    }
    
    /**
     * 更新API
     * @param id API ID
     * @param createApiCommand 更新API命令
     * @return API信息
     */
    @PutMapping("/{id}")
    public SingleResponse<ApiDTO> updateApi(@PathVariable Long id, @Valid @RequestBody CreateApiCommand createApiCommand) {
        log.info("更新API请求: {}", id);
        
        try {
            com.openplatform.api.management.domain.command.CreateApiCommand domainCommand = 
                ApiConverter.toDomain(createApiCommand);
            Api api = apiService.updateApi(id, domainCommand);
            ApiDTO apiDTO = ApiConverter.toApp(api);
            return SingleResponse.of(apiDTO);
        } catch (Exception e) {
            log.error("API更新失败", e);
            return SingleResponse.buildFailure("UPDATE_API_ERROR", "API更新失败: " + e.getMessage());
        }
    }
    
    /**
     * 删除API
     * @param id API ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public Response deleteApi(@PathVariable Long id) {
        log.info("删除API请求: {}", id);
        
        try {
            apiService.deleteApiById(id);
            return Response.buildSuccess();
        } catch (Exception e) {
            log.error("API删除失败", e);
            return Response.buildFailure("DELETE_API_ERROR", "API删除失败: " + e.getMessage());
        }
    }
}