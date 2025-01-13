package com.example.kafka.consumer.service;

import com.example.kafka.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMessageListener {

//    @KafkaListener(topics = "topic-one", groupId = "group-one")
//    public void consume1(String message){
//        log.info("Consumer1 consume the message: {}", message);
//    }
//
//    @KafkaListener(topics = "topic-one", groupId = "group-one")
//    public void consume2(String message){
//        log.info("Consumer2 consume the message: {}", message);
//    }
//
//    @KafkaListener(topics = "topic-one", groupId = "group-one")
//    public void consume3(String message){
//        log.info("Consumer3 consume the message: {}", message);
//    }
//
//    @KafkaListener(topics = "topic-one", groupId = "group-one")
//    public void consume4(String message){
//        log.info("Consumer4 consume the message: {}", message);
//    }

    @KafkaListener(topics = "customer-topic", groupId = "group-customer")
    public void consumeEvents(Customer customer){
        log.info("Consumer consume event: {}", customer);
    }
}
