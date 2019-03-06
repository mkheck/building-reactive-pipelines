package com.thehecklers.brpproc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class Subscriber {
    private String id, firstName, lastName;
    private Instant subscribeDate;
}
