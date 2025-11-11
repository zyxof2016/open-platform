package com.openplatform.user.domain.extension;

/**
 * 扩展点执行器接口
 * @param <C> 扩展点执行上下文
 * @param <R> 扩展点执行结果
 */
public interface ExtensionExecutor<C, R> {
    
    /**
     * 执行扩展点
     * @param context 扩展点执行上下文
     * @return 扩展点执行结果
     */
    R execute(C context);
    
    /**
     * 获取扩展点代码
     * @return 扩展点代码
     */
    String getExtensionCode();
}