package com.academy.cloudstream.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferRequest {
    Double amount;
    String senderId;
    String receiverId;
    String currency;
}
