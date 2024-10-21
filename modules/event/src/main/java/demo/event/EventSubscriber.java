package demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventSubscriber {

    @EventListener
    public void handleEvent(Event event) {
        System.out.println("Received event with message: " + event.getMessage());
    }
}
