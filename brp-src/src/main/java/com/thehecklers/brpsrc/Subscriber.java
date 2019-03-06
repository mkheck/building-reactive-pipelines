package com.thehecklers.brpsrc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
class Subscriber {
    private String id, firstName, lastName;
    private Instant subscribeDate;
}