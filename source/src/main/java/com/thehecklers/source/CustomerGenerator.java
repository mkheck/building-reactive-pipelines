package com.thehecklers.source;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class CustomerGenerator {
    private final String[] names = "Aaron, Belinda, Carla, Dennis, Eugenio, Francine, George, Harold, Irene, Jorge, Kathy, Leona, Mark, Nikhil, Oscar, Philomena".split(", ");
    private final Random rnd = new Random();

    Customer generate() {
        return new Customer(UUID.randomUUID().toString(),
                names[rnd.nextInt(names.length)]);
    }
}