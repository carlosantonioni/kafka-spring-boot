package com.kafkaConsumer.service;

import com.kafkaConsumer.model.LibraryEvent;
import com.kafkaConsumer.model.LibraryEventType;
import com.kafkaConsumer.repository.RepositoryConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class LibraryEventsService {

    @Autowired
    RepositoryConsumer repositoryConsumer;

    @Autowired
    ObjectMapper objectMapper;

    public void processLibraryEvent(ConsumerRecord<Integer, String> consumerRecord) throws IOException {
        LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);

        log.info("Library event : {} ", libraryEvent);

        switch (libraryEvent.getLibraryEventType()) {
            case NEW:
                save(libraryEvent);
                break;
            case UPDATE:
                validate(libraryEvent);
                save(libraryEvent);
                break;
            default:
                log.info("Invalid Library event type");
        }
    }

    private void validate(LibraryEvent libraryEvent) {

        if (libraryEvent.getLibraryEventId()==null) {
            throw new IllegalArgumentException("Library events is not valid");
        }

        Optional<LibraryEvent> existLibraryEvent = repositoryConsumer.findById(libraryEvent.getLibraryEventId());

        if(!existLibraryEvent.isPresent()) {
            throw new IllegalArgumentException("Library event can't be update");
        }

    }

    private void save(LibraryEvent libraryEvent) {
        libraryEvent.getBook().setLibraryEvent(libraryEvent);
        repositoryConsumer.save(libraryEvent);
        log.info("Successfully library saved {} ", libraryEvent);

    }

}
