package demo.event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EventController {

    private final EventPublisher eventPublisher;

    @GetMapping("/publish-event")
    public String publishEvent() {
        eventPublisher.publishEvent("hello world");
        return "ok";
    }
}
