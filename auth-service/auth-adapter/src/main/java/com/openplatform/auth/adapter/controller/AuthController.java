package com.openplatform.auth.adapter.controller;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.openplatform.auth.adapter.converter.AuthConverter;
import com.openplatform.auth.app.command.LoginCommand;
import com.openplatform.auth.app.dto.AuthResponseDTO;
import com.openplatform.auth.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 认证控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    /**
     * 用户登录
     * @param loginCommand 登录命令
     * @return 认证响应
     */
    @PostMapping("/login")
    public SingleResponse<AuthResponseDTO> login(@Valid @RequestBody LoginCommand loginCommand) {
        log.info("用户登录请求: {}", loginCommand.getUsername());
        
        try {
            com.openplatform.auth.domain.command.LoginCommand domainCommand = 
                AuthConverter.toDomain(loginCommand);
            com.openplatform.auth.domain.dto.AuthResponseDTO domainResponse = 
                authService.login(domainCommand);
            AuthResponseDTO appResponse = AuthConverter.toApp(domainResponse);
            return SingleResponse.of(appResponse);
        } catch (Exception e) {
            log.error("登录失败: {}", loginCommand.getUsername(), e);
            return SingleResponse.buildFailure("LOGIN_ERROR", "登录失败: " + e.getMessage());
        }
    }
    
    /**
     * 刷新令牌
     * @param refreshToken 刷新令牌
     * @return 认证响应
     */
    @PostMapping("/refresh")
    public SingleResponse<AuthResponseDTO> refreshToken(@RequestParam String refreshToken) {
        log.info("刷新令牌请求");
        
        try {
            com.openplatform.auth.domain.dto.AuthResponseDTO domainResponse = 
                authService.refreshToken(refreshToken);
            AuthResponseDTO appResponse = AuthConverter.toApp(domainResponse);
            return SingleResponse.of(appResponse);
        } catch (Exception e) {
            log.error("令牌刷新失败", e);
            return SingleResponse.buildFailure("REFRESH_ERROR", "令牌刷新失败: " + e.getMessage());
        }
    }
    
    /**
     * 用户登出
     * @param token 令牌
     * @return 操作结果
     */
    @PostMapping("/logout")
    public Response logout(@RequestHeader("Authorization") String token) {
        log.info("用户登出请求");
        
        try {
            // 移除Bearer前缀
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            
            boolean success = authService.logout(token);
            if (success) {
                return Response.buildSuccess();
            } else {
                return Response.buildFailure("LOGOUT_ERROR", "登出失败");
            }
        } catch (Exception e) {
            log.error("登出失败", e);
            return Response.buildFailure("LOGOUT_ERROR", "登出失败: " + e.getMessage());
        }
    }
    
    /**
     * 验证令牌
     * @param token 令牌
     * @return 验证结果
     */
    @GetMapping("/validate")
    public SingleResponse<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        log.info("验证令牌请求");
        
        try {
            // 移除Bearer前缀
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            
            boolean valid = authService.validateToken(token);
            return SingleResponse.of(valid);
        } catch (Exception e) {
            log.error("令牌验证失败", e);
            return SingleResponse.buildFailure("VALIDATE_ERROR", "令牌验证失败: " + e.getMessage());
        }
    }
}