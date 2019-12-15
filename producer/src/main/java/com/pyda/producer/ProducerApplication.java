package com.pyda.producer;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

@SpringBootApplication
@EnableBinding(value = {ProducerEventBinding.class})
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Component
    @RequiredArgsConstructor
    @Slf4j
    public static class EventGenerator implements ApplicationRunner {

        private final ProducerEventBinding producerEventBinding;

        @Override
        public void run(ApplicationArguments args) {
            Fairy fairy = Fairy.create();
            Runnable runnable = () -> LongStream.range(1, 100)
                                                .forEach(value -> {
                                                    Person person = fairy.person();
                                                    SimpleEvent event = SimpleEvent.of(UUID.randomUUID()
                                                                                           .toString(),
                                                                                       value,
                                                                                       person.getUsername(),
                                                                                       LocalDateTime.now(),
                                                                                       person.getFirstName(),
                                                                                       person.getLastName(),
                                                                                       person.getTelephoneNumber(),
                                                                                       person.getEmail());

                                                    log.info("Event created: {}", event);
                                                    Message<SimpleEvent> message = MessageBuilder
                                                            .withPayload(event)
                                                            .setHeader(KafkaHeaders.MESSAGE_KEY, event.getId().getBytes())
                                                            .build();
                                                    try {
                                                        producerEventBinding.output()
                                                                            .send(message);
                                                    } catch (Exception e) {
                                                        log.error("Error: ", e);
                                                        throw e;
                                                    }
                                                    log.info("Event sent by id: {} ", event.getId());
                                                });
            Executors.newScheduledThreadPool(4)
                     .scheduleAtFixedRate(runnable, 0, 1000, TimeUnit.MILLISECONDS);
        }
    }

}