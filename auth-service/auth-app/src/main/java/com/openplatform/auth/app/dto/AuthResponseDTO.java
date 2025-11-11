package com.openplatform.auth.app.dto;

import lombok.Data;

import java.time.LocalDateTime;

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
    
    private LocalDateTime tokenExpireTime;
    
    private Long userId;
    
    private String username;
}