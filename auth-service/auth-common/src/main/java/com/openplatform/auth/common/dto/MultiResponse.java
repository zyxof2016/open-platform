package com.openplatform.auth.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * COLA架构标准多数据响应类
 * @param <T> 数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiResponse<T> implements Serializable {
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
     * 数据列表
     */
    private List<T> data;

    /**
     * 数据总数
     */
    private Long total;

    /**
     * 创建成功的多数据响应
     * @param data 数据列表
     * @return 成功响应
     */
    public static <T> MultiResponse<T> of(List<T> data) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTotal((long) (data != null ? data.size() : 0));
        return response;
    }

    /**
     * 创建成功的多数据响应
     * @param data 数据列表
     * @param total 数据总数
     * @return 成功响应
     */
    public static <T> MultiResponse<T> of(List<T> data, Long total) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTotal(total);
        return response;
    }

    /**
     * 创建错误响应
     * @param message 错误消息
     * @return 错误响应
     */
    public static <T> MultiResponse<T> failure(String message) {
        MultiResponse<T> response = new MultiResponse<>();
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
    public static <T> MultiResponse<T> failure(String code, String message) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setSuccess(false);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}