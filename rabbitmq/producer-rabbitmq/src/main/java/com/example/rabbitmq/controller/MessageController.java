package com.example.rabbitmq.controller;

import com.example.rabbitmq.model.CustomerDto;
import com.example.rabbitmq.service.EnqueueDequeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private final EnqueueDequeService enqueueDequeService;

    public MessageController(EnqueueDequeService enqueueDequeService) {
        this.enqueueDequeService = enqueueDequeService;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomerDto customerDto) {
        enqueueDequeService.publishMessage(customerDto);
        return "message saved";
    }
}
