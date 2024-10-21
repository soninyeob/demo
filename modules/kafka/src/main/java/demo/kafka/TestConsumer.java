package demo.kafka;

import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log
@Service
public class TestConsumer {

    @KafkaListener(topics = "test-hello")
    public void consumeTestHello1(String message) {
        log.info("consume test-hello1: " + message);
    }


    @KafkaListener(topics = "test-hello")
    public void consumeTestHello2(String message) {
        log.info("consume test-hello2: " + message);
    }
}
