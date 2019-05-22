package com.thehecklers.source;

import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;

@EnableBinding(Source.class)
@AllArgsConstructor
public class DeskAgent {
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