package com.thehecklers.brpsrc;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;

@EnableBinding(Source.class)
class Spammer {
    private SubscriberGenerator generator;

    Spammer(SubscriberGenerator generator) {
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
