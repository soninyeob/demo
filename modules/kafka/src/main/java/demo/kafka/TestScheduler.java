package demo.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TestScheduler {

    private final TestProducer testProducer;

    @Scheduled(fixedRate = 1000)
    public void proudeceMessage() {
        testProducer.produceTestHello("hi " + System.currentTimeMillis());
    }
}
