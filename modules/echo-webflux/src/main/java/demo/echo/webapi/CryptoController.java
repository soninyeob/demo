package demo.echo.webapi;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Base64;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    @PostMapping(value = "/encrypt", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> encrypt(@RequestBody String plainText) {
        return Mono.fromSupplier(() -> {
            String encoded = Base64.getEncoder().encodeToString(plainText.getBytes());
            return "Encrypted Text: " + encoded;
        });
    }

    @PostMapping(value = "/decrypt", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> decrypt(@RequestBody String encryptedText) {
        return Mono.fromSupplier(() -> {
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            return "Decrypted Text: " + new String(decodedBytes);
        });
    }
}
