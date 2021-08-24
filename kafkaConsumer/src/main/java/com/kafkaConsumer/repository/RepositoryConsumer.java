package com.kafkaConsumer.repository;

import com.kafkaConsumer.model.LibraryEvent;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryConsumer extends CrudRepository<LibraryEvent, Integer> {
}
