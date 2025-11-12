package com.openplatform.user.infrastructure.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.core.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.core.plugins.inner.TenantLineInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * MyBatis-Plus多租户配置
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 多租户插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                String tenantId = TenantContext.getCurrentTenantId();
                if (!StringUtils.hasText(tenantId)) {
                    // 使用默认租户ID
                    tenantId = "default";
                }
                return new StringValue(tenantId);
            }

            @Override
            public String getTenantIdColumn() {
                return "tenant_id";
            }

            @Override
            public boolean ignoreTable(String tableName) {
                // 忽略租户过滤的表
                return "op_user".equalsIgnoreCase(tableName) && 
                       "admin".equals(TenantContext.getCurrentTenantId()); // 管理员用户可能需要跨租户查询
            }
        }));
        return interceptor;
    }

    /**
     * 元局字段自动填充处理器
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, "updatedTime", LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, "tenantId", String.class, TenantContext.getCurrentTenantId());
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.strictUpdateFill(metaObject, "updatedTime", LocalDateTime.class, LocalDateTime.now());
            }
        };
    }
}