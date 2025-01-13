package com.example.kafka.producer.service;

import com.example.kafka.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMessagePublisher {
    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendMessageToTopic(String message){
        var completableFuture = template.send("topic-one", message);
        completableFuture.whenComplete((result, throwable) -> {
            if(throwable == null){
                log.info("send message: {} with offset: {}", message, result.getRecordMetadata().offset());
            } else {
                log.info("Unable to send message: {}, due to : {}",message, throwable.getMessage());
            }
        });
    }

    public void sendEventsToTopic(Customer customer){
        try {
            var completableFuture = template.send("customer-topic", customer);
            completableFuture.whenComplete((result, throwable) -> {
                if (throwable == null) {
                    log.info("send message: {} with offset: {}", customer.toString(), result.getRecordMetadata().offset());
                } else {
                    log.info("Unable to send message: {}, due to : {}", customer.toString(), throwable.getMessage());
                }
            });
        }catch (Exception e){
            log.error("Error send events", e);
        }
    }
}
