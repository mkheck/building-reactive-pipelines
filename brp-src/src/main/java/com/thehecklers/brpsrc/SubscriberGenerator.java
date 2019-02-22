package com.thehecklers.brpsrc;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

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
