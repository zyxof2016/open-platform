package com.openplatform.user.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

/**
 * 国际化配置
 */
@Configuration
public class I18nConfig {
    
    /**
     * 配置消息源
     * @return 消息源
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // 设置国际化配置文件的基础路径
        messageSource.setBasename("classpath:i18n/messages");
        // 设置缓存时间（秒）
        messageSource.setCacheSeconds(3600);
        // 设置默认编码
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    /**
     * 配置区域解析器
     * @return 区域解析器
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        // 设置默认区域
        localeResolver.setDefaultLocale(Locale.CHINA);
        return localeResolver;
    }
}