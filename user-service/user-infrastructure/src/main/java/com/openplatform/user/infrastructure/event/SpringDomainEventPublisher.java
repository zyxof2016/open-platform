package com.openplatform.user.infrastructure.event;

import com.openplatform.user.domain.event.DomainEvent;
import com.openplatform.user.domain.event.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 基于Spring的领域事件发布器实现
 */
@Slf4j
@Component
public class SpringDomainEventPublisher implements DomainEventPublisher {
    
    private final ApplicationEventPublisher applicationEventPublisher;
    
    public SpringDomainEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    
    @Override
    public void publish(DomainEvent event) {
        log.info("发布领域事件: {} - {}", event.getEventType(), event.getEventId());
        applicationEventPublisher.publishEvent(event);
    }
}