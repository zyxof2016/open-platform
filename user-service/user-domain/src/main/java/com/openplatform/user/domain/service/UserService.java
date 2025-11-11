package com.openplatform.user.domain.service;

import com.openplatform.user.domain.entity.User;
import com.openplatform.user.domain.event.DomainEventPublisher;
import com.openplatform.user.domain.extension.ExtensionRegistry;

import java.util.List;
import java.util.Optional;

/**
 * 用户领域服务接口
 */
public interface UserService {
    
    /**
     * 设置事件发布器
     * @param eventPublisher 事件发布器
     */
    void setEventPublisher(DomainEventPublisher eventPublisher);
    
    /**
     * 设置扩展点注册器
     * @param extensionRegistry 扩展点注册器
     */
    void setExtensionRegistry(ExtensionRegistry extensionRegistry);
    
    /**
     * 创建用户
     * @param user 用户信息
     * @return 用户信息
     */
    User createUser(User user);
    
    /**
     * 更新用户
     * @param user 用户信息
     * @return 用户信息
     */
    User updateUser(User user);
    
    /**
     * 根据ID删除用户
     * @param id 用户ID
     */
    void deleteUserById(Long id);
    
    /**
     * 根据ID查找用户
     * @param id 用户ID
     * @return 用户信息
     */
    Optional<User> findUserById(Long id);
    
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户信息
     */
    Optional<User> findUserByUsername(String username);
    
    /**
     * 查找所有用户
     * @return 用户列表
     */
    List<User> findAllUsers();
    
    /**
     * 根据ID检查用户是否存在
     * @param id 用户ID
     * @return 是否存在
     */
    boolean existsById(Long id);
}