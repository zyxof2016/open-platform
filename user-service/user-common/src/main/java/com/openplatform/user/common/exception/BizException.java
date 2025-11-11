package com.openplatform.user.common.exception;

/**
 * 业务异常类
 */
public class BizException extends RuntimeException {

    private String errCode;

    public BizException(String errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public BizException(String message) {
        super(message);
        this.errCode = "BIZ_ERROR";
    }

    public BizException(String errCode, String message, Throwable cause) {
        super(message, cause);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }
}