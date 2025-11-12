package com.openplatform.user.common.exception;

/**
 * 系统异常类
 */
public class SysException extends RuntimeException {

    private final String errCode;
    private final String messageKey;

    public SysException(String errCode, String messageKey) {
        super(messageKey);
        this.errCode = errCode;
        this.messageKey = messageKey;
    }

    public SysException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessageKey());
    }

    public SysException(String message) {
        this("SYS_ERROR", message);
    }

    public SysException(String errCode, String messageKey, Throwable cause) {
        super(messageKey, cause);
        this.errCode = errCode;
        this.messageKey = messageKey;
    }

    public SysException(ErrorCode errorCode, Throwable cause) {
        this(errorCode.getCode(), errorCode.getMessageKey(), cause);
    }

    public String getErrCode() {
        return errCode;
    }
    
    public String getMessageKey() {
        return messageKey;
    }
}