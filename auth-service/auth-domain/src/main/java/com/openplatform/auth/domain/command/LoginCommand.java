package com.openplatform.auth.domain.command;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 登录命令
 */
@Data
public class LoginCommand {
    
    @NotBlank(message = "用户名不能为空")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    private String password;
}