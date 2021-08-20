package com.example.kafka.controller;

import com.example.kafka.domain.LibraryEvent;
import com.example.kafka.domain.LibraryEventType;
import com.example.kafka.producer.LibraryEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LibraryEventsController {

    @Autowired
    LibraryEventProducer libraryEventProducer;

    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody @Valid LibraryEvent libraryEvent)
            throws JsonProcessingException {
        // Invoke kafka producer
        libraryEvent.setLibraryEventType(LibraryEventType.NEW);
        libraryEventProducer.sendLibraryEvent(libraryEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }

    @PutMapping("/v1/libraryevent")
    public ResponseEntity<?> putLibraryEvent(@RequestBody @Valid LibraryEvent libraryEvent)
            throws JsonProcessingException {
        if (libraryEvent.getLibraryEventId()==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please entry LibraryEventId");
        }
        libraryEvent.setLibraryEventType(LibraryEventType.UPDATE);
        libraryEventProducer.sendLibraryEvent(libraryEvent);
        return ResponseEntity.status(HttpStatus.OK).body(libraryEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLibraryEvent(@PathVariable("id") Integer id) {
        libraryEventProducer.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}