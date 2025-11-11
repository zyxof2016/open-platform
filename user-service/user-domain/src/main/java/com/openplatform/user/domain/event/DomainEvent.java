package com.openplatform.user.domain.event;

import java.time.LocalDateTime;

/**
 * 领域事件接口
 */
public interface DomainEvent {
    /**
     * 获取事件ID
     */
    String getEventId();
    
    /**
     * 获取事件发生时间
     */
    LocalDateTime getEventTime();
    
    /**
     * 获取事件类型
     */
    String getEventType();
}