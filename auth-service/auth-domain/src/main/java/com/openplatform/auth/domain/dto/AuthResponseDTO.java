package com.openplatform.auth.domain.dto;

import lombok.Data;

/**
 * 认证响应DTO
 */
@Data
public class AuthResponseDTO {
    
    private String token;
    private String refreshToken;
    private String tokenType;
    private Long expiresIn;
    private String scope;
    private Long userId;
    private String username;
}