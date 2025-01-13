package com.example.rabbitmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {
    @Value("${rabbitmq.queue}")
    String  queueName;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleMessage(String message) {
        log.info("Received message from queue {} : {}", queueName, message);
    }
}
