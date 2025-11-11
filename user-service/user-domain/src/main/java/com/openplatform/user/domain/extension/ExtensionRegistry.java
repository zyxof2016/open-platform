package com.openplatform.user.domain.extension;

import java.util.List;

/**
 * 扩展点注册器接口
 */
public interface ExtensionRegistry {
    
    /**
     * 注册扩展点执行器
     * @param executor 扩展点执行器
     */
    void register(ExtensionExecutor<?, ?> executor);
    
    /**
     * 根据扩展点代码获取执行器
     * @param extensionCode 扩展点代码
     * @param contextType 上下文类型
     * @param resultType 结果类型
     * @return 扩展点执行器列表
     */
    <C, R> List<ExtensionExecutor<C, R>> getExecutors(String extensionCode, Class<C> contextType, Class<R> resultType);
}