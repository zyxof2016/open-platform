package com.openplatform.user.infrastructure.repository;

import com.openplatform.user.domain.entity.User;
import com.openplatform.user.domain.repository.UserRepository;
import com.openplatform.user.infrastructure.dataobject.UserDO;
import com.openplatform.user.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户仓储实现类
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    
    private final UserMapper userMapper;
    
    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    @Override
    public User save(User user) {
        UserDO userDO;
        if (user.getId() == null) {
            // 新增
            userDO = new UserDO();
            copyProperties(user, userDO);
            userMapper.insert(userDO);
        } else {
            // 更新
            userDO = new UserDO();
            copyProperties(user, userDO);
            userMapper.updateById(userDO);
        }
        
        // 将DO转换回Entity
        return copyProperties(userDO, new User());
    }
    
    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }
    
    @Override
    public Optional<User> findById(Long id) {
        UserDO userDO = userMapper.selectById(id);
        if (userDO != null) {
            return Optional.of(copyProperties(userDO, new User()));
        }
        return Optional.empty();
    }
    
    @Override
    public Optional<User> findByUsername(String username) {
        // 使用自定义查询
        com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<UserDO> queryWrapper 
            = new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<>();
        queryWrapper.eq("username", username);
        UserDO userDO = userMapper.selectOne(queryWrapper);
        
        if (userDO != null) {
            return Optional.of(copyProperties(userDO, new User()));
        }
        return Optional.empty();
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        // 使用自定义查询
        com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<UserDO> queryWrapper 
            = new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<>();
        queryWrapper.eq("email", email);
        UserDO userDO = userMapper.selectOne(queryWrapper);
        
        if (userDO != null) {
            return Optional.of(copyProperties(userDO, new User()));
        }
        return Optional.empty();
    }
    
    @Override
    public List<User> findAll() {
        List<UserDO> userDOs = userMapper.selectList(null);
        return userDOs.stream()
                .map(userDO -> copyProperties(userDO, new User()))
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean existsById(Long id) {
        return userMapper.selectById(id) != null;
    }
    
    /**
     * 将领域实体复制到数据对象
     */
    private void copyProperties(User entity, UserDO dataObject) {
        dataObject.setId(entity.getId());
        dataObject.setCreatedTime(entity.getCreatedTime());
        dataObject.setUpdatedTime(entity.getUpdatedTime());
        dataObject.setCreatedBy(entity.getCreatedBy());
        dataObject.setUpdatedBy(entity.getUpdatedBy());
        dataObject.setVersion(entity.getVersion());
        dataObject.setUsername(entity.getUsername());
        dataObject.setPassword(entity.getPassword());
        dataObject.setEmail(entity.getEmail());
        dataObject.setPhone(entity.getPhone());
        dataObject.setStatus(entity.getStatus());
        dataObject.setRealName(entity.getRealName());
    }
    
    /**
     * 将数据对象复制到领域实体
     */
    private User copyProperties(UserDO dataObject, User entity) {
        entity.setId(dataObject.getId());
        entity.setCreatedTime(dataObject.getCreatedTime());
        entity.setUpdatedTime(dataObject.getUpdatedTime());
        entity.setCreatedBy(dataObject.getCreatedBy());
        entity.setUpdatedBy(dataObject.getUpdatedBy());
        entity.setVersion(dataObject.getVersion());
        entity.setUsername(dataObject.getUsername());
        entity.setPassword(dataObject.getPassword());
        entity.setEmail(dataObject.getEmail());
        entity.setPhone(dataObject.getPhone());
        entity.setStatus(dataObject.getStatus());
        entity.setRealName(dataObject.getRealName());
        
        return entity;
    }
}