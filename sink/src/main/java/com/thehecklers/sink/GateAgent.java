package com.thehecklers.sink;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@EnableBinding(Sink.class)
public class GateAgent {
    //	@Bean
//	Consumer<FlyingCustomer> logIt() {
//		return System.out::println;
//	}
//
    @Bean
    Consumer<Flux<FlyingCustomer>> logIt() {
        return flux -> flux.subscribe(System.out::println);
    }
}
