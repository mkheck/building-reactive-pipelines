package com.thehecklers.processor;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.function.Function;

@SpringBootApplication
public class ProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessorApplication.class, args);
    }

}

@EnableBinding(Processor.class)
class CustomerTransformer {
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

@Data
@AllArgsConstructor
class FlyingCustomer {
    enum State {
        VALUED,
        PREMIUM,
        ADDITIONAL_SECURITY_SCREENING_AND_SEAT_BY_TOILETS
    }

    private final String id, name;
    private final State state;
}

@Data
@AllArgsConstructor
class Customer {
    private final String id, name;
}