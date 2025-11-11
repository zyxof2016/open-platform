package com.openplatform.auth.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
 * COLA架构标准响应类
 * @param <T> 数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 业务成功标志
     */
    private boolean success;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    /**
     * 创建成功的响应
     * @return 成功响应
     */
    public static Response success() {
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }

    /**
     * 创建带数据的成功响应
     * @param data 数据
     * @return 成功响应
     */
    public static <T> Response of(T data) {
        Response response = new Response();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    /**
     * 创建带数据和消息的成功响应
     * @param data 数据
     * @param message 消息
     * @return 成功响应
     */
    public static <T> Response of(T data, String message) {
        Response response = new Response();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage(message);
        return response;
    }

    /**
     * 创建错误响应
     * @param message 错误消息
     * @return 错误响应
     */
    public static Response failure(String message) {
        Response response = new Response();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    /**
     * 创建带错误码的错误响应
     * @param code 错误码
     * @param message 错误消息
     * @return 错误响应
     */
    public static Response failure(String code, String message) {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}