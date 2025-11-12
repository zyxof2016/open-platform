package com.openplatform.user.common.exception;

/**
 * 业务异常类
 */
public class BizException extends RuntimeException {

    private final String errCode;
    private final String messageKey;

    public BizException(String errCode, String messageKey) {
        super(messageKey);
        this.errCode = errCode;
        this.messageKey = messageKey;
    }

    public BizException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessageKey());
    }

    public BizException(String message) {
        this("BIZ_ERROR", message);
    }

    public BizException(String errCode, String messageKey, Throwable cause) {
        super(messageKey, cause);
        this.errCode = errCode;
        this.messageKey = messageKey;
    }

    public BizException(ErrorCode errorCode, Throwable cause) {
        this(errorCode.getCode(), errorCode.getMessageKey(), cause);
    }

    public String getErrCode() {
        return errCode;
    }
    
    public String getMessageKey() {
        return messageKey;
    }
}