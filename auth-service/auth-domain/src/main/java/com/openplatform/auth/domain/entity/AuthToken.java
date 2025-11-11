package com.openplatform.auth.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 认证令牌实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthToken extends BaseEntity {

    private Long userId;
    private String username;
    private String token;
    private String refreshToken;
    private String tokenType;
    private LocalDateTime expiresAt;
    private String scope;
    private String status;
}