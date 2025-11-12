package com.openplatform.user.infrastructure.interceptor;

import com.openplatform.user.infrastructure.config.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 租户ID拦截器
 */
@Component
public class TenantInterceptor implements HandlerInterceptor {
    
    private static final String TENANT_HEADER = "X-Tenant-ID";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取租户ID
        String tenantId = request.getHeader(TENANT_HEADER);
        
        // 如果请求头中没有租户ID，则从请求参数中获取
        if (!StringUtils.hasText(tenantId)) {
            tenantId = request.getParameter("tenantId");
        }
        
        // 如果仍然没有租户ID，则使用默认值
        if (!StringUtils.hasText(tenantId)) {
            tenantId = "default";
        }
        
        // 设置租户ID到上下文
        TenantContext.setCurrentTenantId(tenantId);
        
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除租户ID上下文
        TenantContext.clear();
    }
}