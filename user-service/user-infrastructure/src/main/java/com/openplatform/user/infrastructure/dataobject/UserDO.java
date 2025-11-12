package com.openplatform.user.infrastructure.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户数据对象
 */
@Data
@TableName("op_user")
public class UserDO {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @TableField("created_time")
    private LocalDateTime createdTime;
    
    @TableField("updated_time")
    private LocalDateTime updatedTime;
    
    @TableField("created_by")
    private String createdBy;
    
    @TableField("updated_by")
    private String updatedBy;
    
    @Version
    @TableField("version")
    private Long version;
    
    @TableField("tenant_id")
    private String tenantId;
    
    @TableField("username")
    private String username;
    
    @TableField("password")
    private String password;
    
    @TableField("email")
    private String email;
    
    @TableField("phone")
    private String phone;
    
    @TableField("status")
    private String status;
    
    @TableField("real_name")
    private String realName;
}