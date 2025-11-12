package com.openplatform.user.common.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 国际化消息工具类
 */
@Component
public class MessageUtils {
    
    private static MessageSource messageSource;
    
    public MessageUtils(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }
    
    /**
     * 获取国际化消息
     * @param code 消息编码
     * @return 国际化消息
     */
    public static String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }
    
    /**
     * 获取国际化消息
     * @param code 消息编码
     * @param args 参数
     * @return 国际化消息
     */
    public static String getMessage(String code, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, locale);
    }
    
    /**
     * 获取指定语言的国际化消息
     * @param code 消息编码
     * @param locale 语言环境
     * @return 国际化消息
     */
    public static String getMessage(String code, Locale locale) {
        return messageSource.getMessage(code, null, locale);
    }
    
    /**
     * 获取指定语言的国际化消息
     * @param code 消息编码
     * @param args 参数
     * @param locale 语言环境
     * @return 国际化消息
     */
    public static String getMessage(String code, Object[] args, Locale locale) {
        return messageSource.getMessage(code, args, locale);
    }
}