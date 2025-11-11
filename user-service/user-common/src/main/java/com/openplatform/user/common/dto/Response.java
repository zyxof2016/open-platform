package com.openplatform.user.common.dto;

import lombok.Data;

/**
 * 响应类
 */
@Data
public class Response {

    private boolean success;
    private String errCode;
    private String errMessage;

    public static Response buildSuccess() {
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }

    public static Response buildFailure(String errCode, String errMessage) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }
}