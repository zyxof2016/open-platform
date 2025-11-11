package com.openplatform.user.domain.repository;

import com.openplatform.user.domain.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * 用户仓储接口
 */
public interface UserRepository {
    
    /**
     * 保存用户
     * @param user 用户信息
     * @return 保存后的用户信息
     */
    User save(User user);
    
    /**
     * 根据ID删除用户
     * @param id 用户ID
     */
    void deleteById(Long id);
    
    /**
     * 根据ID查找用户
     * @param id 用户ID
     * @return 用户信息
     */
    Optional<User> findById(Long id);
    
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户信息
     */
    Optional<User> findByUsername(String username);
    
    /**
     * 根据邮箱查找用户
     * @param email 邮箱
     * @return 用户信息
     */
    Optional<User> findByEmail(String email);
    
    /**
     * 查找所有用户
     * @return 用户列表
     */
    List<User> findAll();
    
    /**
     * 根据ID检查用户是否存在
     * @param id 用户ID
     * @return 是否存在
     */
    boolean existsById(Long id);
}