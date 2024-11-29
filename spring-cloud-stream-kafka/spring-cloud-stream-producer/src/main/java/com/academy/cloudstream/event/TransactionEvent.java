package com.academy.cloudstream.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEvent {
    String transactionId;
    Double amount;
    String senderId;
    String receiverId;
    String type;
    String currency;
    LocalDateTime dateTime;
}
