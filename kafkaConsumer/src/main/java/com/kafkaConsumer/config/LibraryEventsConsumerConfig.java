package com.kafkaConsumer.config;

import com.kafkaConsumer.model.ConsumerEntity;
import com.kafkaConsumer.repository.RepositoryConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class LibraryEventsConsumerConfig {

    @Autowired
    private RepositoryConsumer repositoryConsumer;

    public ConsumerEntity saveConsumer(ConsumerRecord<Integer, String> consumerRecord) {
        ConsumerEntity consumerEntity = new ConsumerEntity();
        consumerEntity.setKey(consumerRecord.key());
        consumerEntity.setValue(consumerRecord.value());
        return repositoryConsumer.save(consumerEntity);
    }
}
