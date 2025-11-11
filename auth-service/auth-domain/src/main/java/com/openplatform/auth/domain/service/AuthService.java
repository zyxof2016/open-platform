package com.openplatform.auth.domain.service;

import com.openplatform.auth.domain.command.LoginCommand;
import com.openplatform.auth.domain.dto.AuthResponseDTO;

/**
 * 认证服务接口
 */
public interface AuthService {
    
    /**
     * 用户登录
     * @param loginCommand 登录命令
     * @return 认证响应
     */
    AuthResponseDTO login(LoginCommand loginCommand);
    
    /**
     * 刷新令牌
     * @param refreshToken 刷新令牌
     * @return 认证响应
     */
    AuthResponseDTO refreshToken(String refreshToken);
    
    /**
     * 用户登出
     * @param token 令牌
     * @return 是否成功
     */
    boolean logout(String token);
    
    /**
     * 验证令牌
     * @param token 令牌
     * @return 是否有效
     */
    boolean validateToken(String token);
}