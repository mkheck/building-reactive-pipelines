package com.thehecklers.brpsrc;

import lombok.Value;

import java.time.Instant;

@Value
class Subscriber {
    private String id, firstName, lastName;
    private Instant subscribeDate;
}