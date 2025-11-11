package com.openplatform.auth.infrastructure.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 认证令牌数据对象
 */
@Data
@TableName("op_auth_token")
public class AuthTokenDO {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @TableField("created_time")
    private LocalDateTime createdTime;
    
    @TableField("updated_time")
    private LocalDateTime updatedTime;
    
    @Version
    @TableField("version")
    private Long version;
    
    @TableField("user_id")
    private Long userId;
    
    @TableField("username")
    private String username;
    
    @TableField("token")
    private String token;
    
    @TableField("refresh_token")
    private String refreshToken;
    
    @TableField("token_type")
    private String tokenType;
    
    @TableField("expires_in")
    private LocalDateTime expiresAt;
    
    @TableField("scope")
    private String scope;
    
    @TableField("status")
    private String status;
}