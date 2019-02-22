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

@SpringBootApplication
public class BrpSrcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrpSrcApplication.class, args);
    }

}
