package com.openplatform.user.common.exception;

/**
 * 错误码枚举
 */
public enum ErrorCode {
    
    // 用户相关错误码 (100000-199999)
    USER_NOT_FOUND("100001", "user.not.found"),
    USER_ALREADY_EXISTS("100002", "user.already.exists"),
    USER_CREATE_SUCCESS("100003", "user.create.success"),
    USER_UPDATE_SUCCESS("100004", "user.update.success"),
    USER_DELETE_SUCCESS("100005", "user.delete.success"),
    USER_USERNAME_REQUIRED("100006", "user.username.required"),
    USER_PASSWORD_REQUIRED("100007", "user.password.required"),
    USER_EMAIL_INVALID("100008", "user.email.invalid"),
    USER_PHONE_INVALID("100009", "user.phone.invalid"),
    USER_STATUS_INVALID("100010", "user.status.invalid"),
    
    // 系统相关错误码 (900000-999999)
    SYSTEM_ERROR("900001", "system.error"),
    PARAMETER_ERROR("900002", "parameter.error"),
    UNAUTHORIZED("900003", "unauthorized"),
    FORBIDDEN("900004", "forbidden"),
    NOT_FOUND("900005", "not.found"),
    METHOD_NOT_ALLOWED("900006", "method.not.allowed"),
    INTERNAL_SERVER_ERROR("900007", "internal.server.error");
    
    private final String code;
    private final String messageKey;
    
    ErrorCode(String code, String messageKey) {
        this.code = code;
        this.messageKey = messageKey;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getMessageKey() {
        return messageKey;
    }
    
    @Override
    public String toString() {
        return code + ": " + messageKey;
    }
}