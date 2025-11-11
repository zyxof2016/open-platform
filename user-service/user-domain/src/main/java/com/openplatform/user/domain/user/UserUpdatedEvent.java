package com.openplatform.user.domain.user;

import com.openplatform.user.domain.entity.User;
import com.openplatform.user.domain.event.AbstractDomainEvent;
import lombok.Getter;

/**
 * 用户更新事件
 */
@Getter
public class UserUpdatedEvent extends AbstractDomainEvent {
    
    private final User user;
    
    public UserUpdatedEvent(User user) {
        this.user = user;
    }
}