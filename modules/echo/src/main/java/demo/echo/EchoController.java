package demo.echo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EchoController {

    @PostMapping("/encrypt")
    public String encrypt() {
        return "echo: encrypt";
    }

    @PostMapping("/decrypt")
    public String decrypt() {
        return "echo: decrypt";
    }
}
