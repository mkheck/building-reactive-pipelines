package com.thehecklers.brpsink;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@EnableBinding(Sink.class)
public class MessageProcessor {
    @Bean
    Consumer<Flux<Subscriber>> sinkIt() {
        return subscriberFlux -> subscriberFlux.subscribe(System.out::println);
    }
}
