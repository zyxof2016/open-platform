package com.openplatform.auth.app.command;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 用户登录命令
 */
@Data
public class LoginCommand {
    
    @NotBlank(message = "用户名不能为空")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    private String password;
}