package demo.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class Event extends ApplicationEvent {

    private final String message;

    public Event(Object source, String message) {
        super(source);
        this.message = message;
    }
}
