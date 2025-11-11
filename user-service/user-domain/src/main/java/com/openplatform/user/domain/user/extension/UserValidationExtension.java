package com.openplatform.user.domain.user.extension;

import com.openplatform.user.domain.extension.ExtensionExecutor;

/**
 * 用户验证扩展点接口
 */
public interface UserValidationExtension extends ExtensionExecutor<UserValidationContext, UserValidationResult> {
    @Override
    default String getExtensionCode() {
        return "USER_VALIDATION";
    }
}