package com.openplatform.user.domain.user.extension;

import com.openplatform.user.domain.entity.User;

/**
 * 用户验证扩展点上下文
 */
public class UserValidationContext {
    private final User user;
    private final String operationType; // CREATE, UPDATE, DELETE
    
    public UserValidationContext(User user, String operationType) {
        this.user = user;
        this.operationType = operationType;
    }
    
    public User getUser() {
        return user;
    }
    
    public String getOperationType() {
        return operationType;
    }
}