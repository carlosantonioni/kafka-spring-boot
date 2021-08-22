package com.kafkaConsumer.consumer;

import com.kafkaConsumer.config.LibraryEventsConsumerConfig;
import com.kafkaConsumer.model.ConsumerEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class LibraryEventsConsumer {

    @Autowired
    private LibraryEventsConsumerConfig libraryEventsConsumerConfig;

    @KafkaListener(topics = {"library-events"})
    public ConsumerEntity onMessage(ConsumerRecord<Integer, String> consumerRecord) {

        log.info("ConsumerRecord : {} ", consumerRecord);

        return libraryEventsConsumerConfig.saveConsumer(consumerRecord);

    }
}
