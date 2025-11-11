package com.openplatform.user.domain.event;

/**
 * 领域事件发布器接口
 */
public interface DomainEventPublisher {
    
    /**
     * 发布领域事件
     * 
     * @param event 领域事件
     * @param <T> 事件类型
     */
    <T extends DomainEvent> void publish(T event);
}