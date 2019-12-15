package com.pyda.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

interface ProducerEventBinding {

    String SIMPLE_EVENT_OUTPUT = "simple-event-output";

    @Output(SIMPLE_EVENT_OUTPUT)
    MessageChannel output();
}
