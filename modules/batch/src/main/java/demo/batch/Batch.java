package demo.batch;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Batch {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Batch.class)
                .run(args);
    }
}
