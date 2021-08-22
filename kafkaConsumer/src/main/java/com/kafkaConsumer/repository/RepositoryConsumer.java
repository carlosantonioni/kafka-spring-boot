package com.kafkaConsumer.repository;

import com.kafkaConsumer.model.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryConsumer extends JpaRepository<ConsumerEntity, Long> {
}
