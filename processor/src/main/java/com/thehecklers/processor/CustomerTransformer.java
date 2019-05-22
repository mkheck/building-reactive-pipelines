package com.thehecklers.processor;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.function.Function;

@EnableBinding(Processor.class)
public class CustomerTransformer {
    private final Random rnd = new Random();

    @Bean
    Function<Customer, FlyingCustomer> addState() {
        return cust -> new FlyingCustomer(cust.getId(),
                cust.getName(),
                rnd.nextInt(5) == 0 ? FlyingCustomer.State.PREMIUM : FlyingCustomer.State.VALUED);
    }

    @Bean
    Function<Flux<FlyingCustomer>, Flux<FlyingCustomer>> treatMark() {
        return flux -> flux.map(fc -> new FlyingCustomer(fc.getId(),
                fc.getName(),
                fc.getName().equalsIgnoreCase("mark") ? FlyingCustomer.State.ADDITIONAL_SECURITY_SCREENING_AND_SEAT_BY_TOILETS : fc.getState()));
    }
}