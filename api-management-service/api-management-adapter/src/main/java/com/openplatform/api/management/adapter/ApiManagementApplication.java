package com.openplatform.api.management.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * API管理服务启动类
 */
@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy
public class ApiManagementApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ApiManagementApplication.class, args);
    }
}