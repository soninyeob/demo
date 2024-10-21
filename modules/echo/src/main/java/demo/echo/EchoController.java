package demo.echo;

import com.google.common.base.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @GetMapping("/echo")
    public String echoParam(@RequestParam(required = false) String value) {
        return "echo:param [" + Strings.nullToEmpty(value) + "]";
    }

    @GetMapping("/echo/{value}")
    public String echoPath(@PathVariable String value) {
        return "echo:path [" + value + "]";
    }
}
