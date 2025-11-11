package com.openplatform.user.common.dto;

import lombok.Data;

/**
 * 单一响应类
 */
@Data
public class SingleResponse<T> extends Response {

    private T data;

    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setData(data);
        response.setSuccess(true);
        return response;
    }

    public static <T> SingleResponse<T> buildFailure(String errCode, String errMessage) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }
}