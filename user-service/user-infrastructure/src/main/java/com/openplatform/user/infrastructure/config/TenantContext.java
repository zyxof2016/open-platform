package com.openplatform.user.infrastructure.config;

/**
 * 租户上下文持有者
 */
public class TenantContext {
    
    private static final ThreadLocal<String> tenantIdContext = new ThreadLocal<>();
    
    /**
     * 设置当前租户ID
     * @param tenantId 租户ID
     */
    public static void setCurrentTenantId(String tenantId) {
        tenantIdContext.set(tenantId);
    }
    
    /**
     * 获取当前租户ID
     * @return 租户ID
     */
    public static String getCurrentTenantId() {
        return tenantIdContext.get();
    }
    
    /**
     * 清除租户ID
     */
    public static void clear() {
        tenantIdContext.remove();
    }
}