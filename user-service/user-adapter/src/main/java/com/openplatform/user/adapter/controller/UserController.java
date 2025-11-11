package com.openplatform.user.adapter.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.openplatform.user.adapter.converter.UserConverter;
import com.openplatform.user.app.command.CreateUserCommand;
import com.openplatform.user.app.dto.UserDTO;
import com.openplatform.user.domain.entity.User;
import com.openplatform.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    /**
     * 创建用户
     * @param command 创建用户命令
     * @return 用户信息
     */
    @PostMapping
    public SingleResponse<UserDTO> createUser(@Valid @RequestBody CreateUserCommand command) {
        log.info("创建用户: {}", command.getUsername());
        
        try {
            // 创建用户实体
            User user = UserConverter.toEntity(command);
            User savedUser = userService.createUser(user);
            
            UserDTO userDTO = UserConverter.toDTO(savedUser);
            return SingleResponse.of(userDTO);
        } catch (Exception e) {
            log.error("创建用户失败", e);
            return SingleResponse.buildFailure("CREATE_ERROR", "创建用户失败: " + e.getMessage());
        }
    }
    
    /**
     * 根据ID获取用户
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public SingleResponse<UserDTO> getUserById(@PathVariable Long id) {
        log.info("获取用户ID: {}", id);
        
        return userService.findUserById(id)
                .map(user -> SingleResponse.of(UserConverter.toDTO(user)))
                .orElse(SingleResponse.buildFailure("NOT_FOUND", "用户不存在"));
    }
    
    /**
     * 获取所有用户
     * @return 用户列表
     */
    @GetMapping
    public MultiResponse<UserDTO> getAllUsers() {
        log.info("获取所有用户");
        
        List<User> users = userService.findAllUsers();
        List<UserDTO> userDTOs = users.stream()
                .map(UserConverter::toDTO)
                .collect(Collectors.toList());
        
        return MultiResponse.of(userDTOs);
    }
    
    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户信息
     */
    @GetMapping("/username/{username}")
    public SingleResponse<UserDTO> getUserByUsername(@PathVariable String username) {
        log.info("根据用户名获取用户: {}", username);
        
        return userService.findUserByUsername(username)
                .map(user -> SingleResponse.of(UserConverter.toDTO(user)))
                .orElse(SingleResponse.buildFailure("NOT_FOUND", "用户不存在"));
    }
    
    /**
     * 更新用户
     * @param id 用户ID
     * @param command 创建用户命令
     * @return 用户信息
     */
    @PutMapping("/{id}")
    public SingleResponse<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody CreateUserCommand command) {
        log.info("更新用户ID: {}, 用户名: {}", id, command.getUsername());
        
        try {
            // 检查用户是否存在
            if (!userService.existsById(id)) {
                return SingleResponse.buildFailure("NOT_FOUND", "用户不存在");
            }
            
            // 获取现有用户
            User existingUser = userService.findUserById(id).orElse(null);
            if (existingUser == null) {
                return SingleResponse.buildFailure("NOT_FOUND", "用户不存在");
            }
            
            // 更新用户信息
            existingUser.setUsername(command.getUsername());
            existingUser.setPassword(command.getPassword());
            existingUser.setEmail(command.getEmail());
            existingUser.setPhone(command.getPhone());
            existingUser.setRealName(command.getRealName());
            
            User updatedUser = userService.updateUser(existingUser);
            UserDTO userDTO = UserConverter.toDTO(updatedUser);
            return SingleResponse.of(userDTO);
        } catch (Exception e) {
            log.error("更新用户失败", e);
            return SingleResponse.buildFailure("UPDATE_ERROR", "用户更新失败: " + e.getMessage());
        }
    }
    
    /**
     * 删除用户
     * @param id 用户ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable Long id) {
        log.info("删除用户ID: {}", id);
        
        try {
            userService.deleteUserById(id);
            return Response.buildSuccess();
        } catch (Exception e) {
            log.error("删除用户失败", e);
            return Response.buildFailure("DELETE_ERROR", "用户删除失败: " + e.getMessage());
        }
    }
}