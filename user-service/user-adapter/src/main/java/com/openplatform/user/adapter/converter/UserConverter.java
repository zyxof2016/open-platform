package com.openplatform.user.adapter.converter;

import com.openplatform.user.app.command.CreateUserCommand;
import com.openplatform.user.app.dto.UserDTO;
import com.openplatform.user.domain.entity.User;

/**
 * 用户转换器
 */
public class UserConverter {
    
    /**
     * 将创建用户命令转换为用户实体
     * @param command 创建用户命令
     * @return 用户实体
     */
    public static User toEntity(CreateUserCommand command) {
        User user = new User();
        user.setUsername(command.getUsername());
        user.setPassword(command.getPassword());
        user.setEmail(command.getEmail());
        user.setPhone(command.getPhone());
        user.setRealName(command.getRealName());
        user.setStatus("ACTIVE"); // 默认激活状态
        return user;
    }
    
    /**
     * 将用户实体转换为用户DTO
     * @param user 用户实体
     * @return 用户DTO
     */
    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setRealName(user.getRealName());
        userDTO.setStatus(user.getStatus());
        userDTO.setCreatedTime(user.getCreatedTime());
        userDTO.setUpdatedTime(user.getUpdatedTime());
        return userDTO;
    }
}