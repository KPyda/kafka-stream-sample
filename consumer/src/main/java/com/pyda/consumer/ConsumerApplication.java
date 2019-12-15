package com.pyda.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableBinding({ConsumerEventBinding.class})
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Component
    @Slf4j
    public static class EventListener {

        @StreamListener
        public void process(@Input(ConsumerEventBinding.SIMPLE_EVENT_INPUT) KStream<String, SimpleEvent> stream) {
            stream.foreach((s, simpleEvent) -> log.info("Processing event by key: {} - value: {}", s, simpleEvent));
        }

    }

}
