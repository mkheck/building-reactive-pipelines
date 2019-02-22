package com.thehecklers.brpsrc;

import lombok.Value;
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
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

@EnableBinding(Source.class)
@SpringBootApplication
public class BrpSrcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrpSrcApplication.class, args);
    }

}

@Component
class Spammer {
    private Source source;
    private SubscriberGenerator generator;

    Spammer(Source source, SubscriberGenerator generator) {
        this.source = source;
        this.generator = generator;
    }

    @PostConstruct
    @StreamEmitter
    @Output(Source.OUTPUT)
    Flux<Subscriber> send() {
        return Flux.interval(Duration.ofMillis(1000))
                .onBackpressureDrop()
                .map(l -> generator.generate());
    }
}

@Component
class SubscriberGenerator {
    private String[] firstNames = "Alpha, Bravo, Charlie, Delta, Echo, Foxtrot, Golf, Hotel".split(", ");
    private String[] lastNames = "Alpha, Bravo, Charlie, Delta, Echo, Foxtrot, Golf, Hotel".split(", ");

    Subscriber generate() {
        Random random = new Random();
        int i = random.nextInt(8);
        int j = random.nextInt(8);

        return new Subscriber(UUID.randomUUID().toString(), firstNames[i], lastNames[j], Instant.now());
    }
}

@Value
class Subscriber {
    private String id, firstName, lastName;
    private Instant subscribeDate;
}