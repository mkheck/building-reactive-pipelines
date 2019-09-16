package com.thehecklers.sink;

import lombok.Value;
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
class Attendant {
	@Bean
	Consumer<Flux<FlyingPassenger>> logPassenger() {
		return flux -> flux.subscribe(System.out::println);
	}
}

@Value
class FlyingPassenger {
	enum Status {
		VALUED,
		PREMIUM,
		ADDITIONAL_SECURITY_SCREENING_SEAT_BY_TOILETS
	}

	private final String id, name;
	private final Status status;
}