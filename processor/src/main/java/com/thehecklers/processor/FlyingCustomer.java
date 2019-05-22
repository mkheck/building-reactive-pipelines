package com.thehecklers.processor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlyingCustomer {
    enum State {
        VALUED,
        PREMIUM,
        ADDITIONAL_SECURITY_SCREENING_AND_SEAT_BY_TOILETS
    }

    private final String id, name;
    private final State state;
}
