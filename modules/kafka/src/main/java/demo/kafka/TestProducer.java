package demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log
@RequiredArgsConstructor
@Service
public class TestProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void produceTestHello(String message) {
        kafkaTemplate.send("test-hello", message);
    }
}
