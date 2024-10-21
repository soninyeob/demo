package demo.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final TestProducer producerService;

    @GetMapping("/hello")
    public void hello(@RequestParam("message") String message) {
        producerService.produceTestHello(message);
    }
}
