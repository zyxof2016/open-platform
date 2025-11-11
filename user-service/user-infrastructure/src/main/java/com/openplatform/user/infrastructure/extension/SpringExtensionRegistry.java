package com.openplatform.user.infrastructure.extension;

import com.openplatform.user.domain.extension.ExtensionExecutor;
import com.openplatform.user.domain.extension.ExtensionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于Spring的扩展点注册器实现
 */
@Slf4j
@Component
public class SpringExtensionRegistry implements ExtensionRegistry {
    
    private final Map<String, List<ExtensionExecutor<?, ?>>> extensionMap = new ConcurrentHashMap<>();
    
    @Override
    public void register(ExtensionExecutor<?, ?> executor) {
        String extensionCode = executor.getExtensionCode();
        extensionMap.computeIfAbsent(extensionCode, k -> new ArrayList<>()).add(executor);
        log.info("注册扩展点执行器: {}", extensionCode);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <C, R> List<ExtensionExecutor<C, R>> getExecutors(String extensionCode, Class<C> contextType, Class<R> resultType) {
        List<ExtensionExecutor<C, R>> executors = new ArrayList<>();
        List<ExtensionExecutor<?, ?>> registeredExecutors = extensionMap.get(extensionCode);
        
        if (registeredExecutors != null) {
            for (ExtensionExecutor<?, ?> executor : registeredExecutors) {
                if (executor.getExtensionCode().equals(extensionCode)) {
                    executors.add((ExtensionExecutor<C, R>) executor);
                }
            }
        }
        
        log.info("获取扩展点执行器: {}, 数量: {}", extensionCode, executors.size());
        return executors;
    }
}