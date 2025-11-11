package com.openplatform.user.common.exception;

/**
 * 系统异常类
 */
public class SysException extends RuntimeException {

    private String errCode;

    public SysException(String errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public SysException(String message) {
        super(message);
        this.errCode = "SYS_ERROR";
    }

    public SysException(String errCode, String message, Throwable cause) {
        super(message, cause);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }
}