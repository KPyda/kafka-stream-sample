package com.pyda.producer;

import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
class SimpleEvent {
    private final String id;
    private final Long clientId;
    private final String username;
    private final LocalDateTime time;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String email;
}
