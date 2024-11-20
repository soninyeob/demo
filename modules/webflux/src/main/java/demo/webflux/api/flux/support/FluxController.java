package demo.webflux.api.flux.support;

import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
public class FluxController {

    // curl -s http://localhost:9002/mono\?value\=1 | jq
    @GetMapping("/echo/mono")
    public Mono<String> monoParam(@RequestParam(required = false) String value) {
        return Mono.just("mono:param [" + Strings.nullToEmpty(value) + "]");
    }

    // curl -s http://localhost:9002/mono/1 | jq
    @GetMapping("/echo/mono/{path}")
    public Mono<String> monoPath(@PathVariable String path) {
        return Mono.just("echo:path [" + path + "]");
    }

    // curl -s -X POST http://localhost:9002/mono -d '{"message": "Hello WebFlux!"}' -H "Content-Type: application/json" | jq
    @PostMapping("/echo/mono")
    public Mono<String> monoBody(@RequestBody String message) {
        return Mono.just(message);
    }

    @GetMapping("/echo/flux")
    public Flux<String> flux() {
        return Flux.just("Hello", "World");
    }

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> sse() {
        return Flux.interval(Duration.ofMillis(100L))
                .map(sequence -> "Flux event " + sequence);
    }
}
