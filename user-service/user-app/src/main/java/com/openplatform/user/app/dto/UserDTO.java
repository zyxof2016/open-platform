package com.openplatform.user.app.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户数据传输对象
 */
@Data
public class UserDTO {
    
    private Long id;
    
    private String username;
    
    private String email;
    
    private String phone;
    
    private String status;
    
    private String realName;
    
    private LocalDateTime createdTime;
    
    private LocalDateTime updatedTime;
}