package com.thehecklers.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceApplication.class, args);
    }

}

@EnableBinding(Source.class)
@AllArgsConstructor
class DeskAgent {
    private final CustomerGenerator generator;

    @StreamEmitter
    @Output(Source.OUTPUT)
    @PostConstruct
    Flux<Customer> sendCustomers() {
        return Flux.interval(Duration.ofSeconds(1))
                .onBackpressureDrop()
                .map(l -> generator.generate());
    }
}

@Component
class CustomerGenerator {
    private final String[] names = "Aaron, Belinda, Carla, Dennis, Eugenio, Francine, George, Harold, Irene, Jorge, Kathy, Leona, Mark, Nikhil, Oscar, Philomena".split(", ");
    private final Random rnd = new Random();

    Customer generate() {
        return new Customer(UUID.randomUUID().toString(),
                names[rnd.nextInt(names.length)]);
    }
}

@Data
@AllArgsConstructor
class Customer {
    private final String id, name;
}