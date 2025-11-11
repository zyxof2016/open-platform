package com.openplatform.user.domain.user;

import com.openplatform.user.domain.event.AbstractDomainEvent;
import lombok.Getter;

/**
 * 用户删除事件
 */
@Getter
public class UserDeletedEvent extends AbstractDomainEvent {
    
    private final Long userId;
    private final String username;
    
    public UserDeletedEvent(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}