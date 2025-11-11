package com.openplatform.user.domain.user;

import com.openplatform.user.domain.entity.User;
import com.openplatform.user.domain.event.AbstractDomainEvent;
import lombok.Getter;

/**
 * 用户创建事件
 */
@Getter
public class UserCreatedEvent extends AbstractDomainEvent {
    
    private final User user;
    
    public UserCreatedEvent(User user) {
        this.user = user;
    }
}