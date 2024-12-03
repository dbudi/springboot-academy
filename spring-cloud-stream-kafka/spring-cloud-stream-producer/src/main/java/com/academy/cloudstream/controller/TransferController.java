package com.academy.cloudstream.controller;

import com.academy.cloudstream.dto.TransferRequest;
import com.academy.cloudstream.event.TransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    private final StreamBridge streamBridge;

    private static final String OUTPUT_BINDING = "sendTransfer-out-0";

    @Autowired
    public TransferController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest){
        TransactionEvent transactionEvent = createTransactionEvent(transferRequest);
        log.info("Sending data: {} to binding: {}", transactionEvent, OUTPUT_BINDING);
        streamBridge.send(OUTPUT_BINDING, transactionEvent);
        return ResponseEntity.ok("Transfer Successfully, transactionId="+transactionEvent.getTransactionId());
    }

    private TransactionEvent createTransactionEvent(TransferRequest transferRequest){
        return TransactionEvent.builder()
                .transactionId(RandomStringUtils.randomAlphanumeric(8) + RandomUtils.nextInt())
                .amount(transferRequest.getAmount())
                .senderId(transferRequest.getSenderId())
                .receiverId(transferRequest.getReceiverId())
                .type("TRANSFER")
                .currency("IDR")
                .dateTime(LocalDateTime.now())
                .build();
    }
}
