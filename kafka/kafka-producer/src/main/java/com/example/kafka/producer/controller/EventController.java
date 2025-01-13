package com.example.kafka.producer.controller;

import com.example.kafka.dto.Customer;
import com.example.kafka.producer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class EventController {
    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
        try {
            for(int i=0; i<10000; i++) {
                publisher.sendMessageToTopic(message + ": "+i);
            }
            return ResponseEntity.ok("message published successfully");
        }catch (Exception exception){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publishEvents(@RequestBody Customer customer){
        try {
            publisher.sendEventsToTopic(customer);
            return ResponseEntity.ok("Events published successfully");
        }catch (Exception exception){
            return ResponseEntity.internalServerError().build();
        }
    }
}
