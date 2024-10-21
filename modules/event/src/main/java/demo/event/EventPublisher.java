package demo.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishEvent(String message) {
        Event event = new Event(this, message);
        eventPublisher.publishEvent(event);
    }
}
