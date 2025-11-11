package com.openplatform.user.domain.service.impl;

import com.openplatform.user.domain.entity.User;
import com.openplatform.user.domain.event.DomainEventPublisher;
import com.openplatform.user.domain.extension.ExtensionRegistry;
import com.openplatform.user.domain.repository.UserRepository;
import com.openplatform.user.domain.service.UserService;
import com.openplatform.user.domain.user.UserCreatedEvent;
import com.openplatform.user.domain.user.UserDeletedEvent;
import com.openplatform.user.domain.user.UserUpdatedEvent;
import com.openplatform.user.domain.user.extension.UserValidationContext;
import com.openplatform.user.domain.user.extension.UserValidationExtension;
import com.openplatform.user.domain.user.extension.UserValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 用户领域服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private DomainEventPublisher eventPublisher;
    private ExtensionRegistry extensionRegistry;
    
    @Override
    public void setEventPublisher(DomainEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    
    @Override
    public void setExtensionRegistry(ExtensionRegistry extensionRegistry) {
        this.extensionRegistry = extensionRegistry;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User createUser(User user) {
        log.info("创建用户: {}", user.getUsername());
        
        // 使用扩展点进行用户验证
        validateUser(user, "CREATE");
        
        User savedUser = userRepository.save(user);
        
        // 发布用户创建事件
        if (eventPublisher != null) {
            eventPublisher.publish(new UserCreatedEvent(savedUser));
        }
        
        return savedUser;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updateUser(User user) {
        log.info("更新用户: {}", user.getId());
        
        // 使用扩展点进行用户验证
        validateUser(user, "UPDATE");
        
        User updatedUser = userRepository.save(user);
        
        // 发布用户更新事件
        if (eventPublisher != null) {
            eventPublisher.publish(new UserUpdatedEvent(updatedUser));
        }
        
        return updatedUser;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserById(Long id) {
        log.info("删除用户: {}", id);
        
        // 先获取用户信息用于事件发布和验证
        Optional<User> userOptional = userRepository.findById(id);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // 使用扩展点进行删除前验证
            validateUser(user, "DELETE");
        }
        
        userRepository.deleteById(id);
        
        // 发布用户删除事件
        if (eventPublisher != null && userOptional.isPresent()) {
            User user = userOptional.get();
            eventPublisher.publish(new UserDeletedEvent(user.getId(), user.getUsername()));
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) {
        log.info("根据ID查找用户: {}", id);
        return userRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByUsername(String username) {
        log.info("根据用户名查找用户: {}", username);
        return userRepository.findByUsername(username);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        log.info("查找所有用户");
        return userRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        log.info("检查用户是否存在: {}", id);
        return userRepository.existsById(id);
    }
    
    /**
     * 使用扩展点验证用户
     * @param user 用户
     * @param operationType 操作类型
     */
    private void validateUser(User user, String operationType) {
        if (extensionRegistry != null) {
            UserValidationContext context = new UserValidationContext(user, operationType);
            @SuppressWarnings("unchecked")
            List<UserValidationExtension> extensions = (List<UserValidationExtension>) (List<?>) extensionRegistry.getExecutors(
                "USER_VALIDATION", UserValidationContext.class, UserValidationResult.class);
            
            for (UserValidationExtension extension : extensions) {
                UserValidationResult result = extension.execute(context);
                if (!result.isValid()) {
                    throw new IllegalArgumentException("用户验证失败: " + result.getErrorMessage());
                }
            }
        }
    }
}