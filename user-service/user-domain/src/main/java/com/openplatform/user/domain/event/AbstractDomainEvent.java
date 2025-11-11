package com.openplatform.user.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 抽象领域事件基类
 */
public abstract class AbstractDomainEvent implements DomainEvent {
    
    private final String eventId;
    private final LocalDateTime eventTime;
    private final String eventType;
    
    public AbstractDomainEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.eventTime = LocalDateTime.now();
        this.eventType = this.getClass().getSimpleName();
    }
    
    @Override
    public String getEventId() {
        return eventId;
    }
    
    @Override
    public LocalDateTime getEventTime() {
        return eventTime;
    }
    
    @Override
    public String getEventType() {
        return eventType;
    }
}