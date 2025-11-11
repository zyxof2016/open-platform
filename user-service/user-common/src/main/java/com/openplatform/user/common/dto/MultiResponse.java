package com.openplatform.user.common.dto;

import lombok.Data;

import java.util.List;

/**
 * 多响应类
 */
@Data
public class MultiResponse<T> extends Response {

    private List<T> data;

    public static <T> MultiResponse<T> of(List<T> data) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setData(data);
        response.setSuccess(true);
        return response;
    }

    public static <T> MultiResponse<T> buildFailure(String errCode, String errMessage) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }
}