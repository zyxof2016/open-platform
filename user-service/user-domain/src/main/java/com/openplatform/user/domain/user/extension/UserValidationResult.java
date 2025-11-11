package com.openplatform.user.domain.user.extension;

/**
 * 用户验证扩展点结果
 */
public class UserValidationResult {
    private boolean valid;
    private String errorMessage;
    
    public UserValidationResult(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
}