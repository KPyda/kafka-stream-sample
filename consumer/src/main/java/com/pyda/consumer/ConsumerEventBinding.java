package com.pyda.consumer;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface ConsumerEventBinding {
    String SIMPLE_EVENT_INPUT = "simple-event-input";

    @Input(SIMPLE_EVENT_INPUT)
    KStream<String, SimpleEvent> output();
}
