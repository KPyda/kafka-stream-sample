package com.pyda.consumer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public SimpleEvent(@JsonProperty("id") String id,
                       @JsonProperty("clientId") Long clientId,
                       @JsonProperty("username") String username,
                       @JsonProperty("time") LocalDateTime time,
                       @JsonProperty("firstName") String firstName,
                       @JsonProperty("lastName") String lastName,
                       @JsonProperty("phone") String phone,
                       @JsonProperty("email") String email) {

        this.id = id;
        this.clientId = clientId;
        this.username = username;
        this.time = time;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
}
