package com.openplatform.user.infrastructure.extension.user;

import com.openplatform.user.domain.user.extension.UserValidationContext;
import com.openplatform.user.domain.user.extension.UserValidationExtension;
import com.openplatform.user.domain.user.extension.UserValidationResult;
import org.springframework.stereotype.Component;

/**
 * 默认用户验证扩展点实现
 */
@Component
public class DefaultUserValidationExtension implements UserValidationExtension {
    
    @Override
    public UserValidationResult execute(UserValidationContext context) {
        // 基本验证逻辑
        if (context.getUser().getUsername() == null || context.getUser().getUsername().isEmpty()) {
            return new UserValidationResult(false, "用户名不能为空");
        }
        
        if (context.getUser().getUsername().length() < 4) {
            return new UserValidationResult(false, "用户名长度不能少于4个字符");
        }
        
        if (context.getUser().getPassword() == null || context.getUser().getPassword().isEmpty()) {
            return new UserValidationResult(false, "密码不能为空");
        }
        
        if (context.getUser().getPassword().length() < 6) {
            return new UserValidationResult(false, "密码长度不能少于6个字符");
        }
        
        return new UserValidationResult(true, null);
    }
}