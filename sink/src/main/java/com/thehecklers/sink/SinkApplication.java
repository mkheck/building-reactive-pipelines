package com.thehecklers.sink;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@SpringBootApplication
public class SinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinkApplication.class, args);
	}

}

@EnableBinding(Sink.class)
class GateAgent {
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

@Data@AllArgsConstructor
class FlyingCustomer {
	enum State {
		VALUED,
		PREMIUM,
		ADDITIONAL_SECURITY_SCREENING_AND_SEAT_BY_TOILETS
	}

	private final String id, name;
	private final State state;
}