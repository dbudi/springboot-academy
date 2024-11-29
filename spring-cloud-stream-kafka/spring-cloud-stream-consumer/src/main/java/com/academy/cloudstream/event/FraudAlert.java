package com.academy.cloudstream.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FraudAlert {
    String transactionId;
    String type;
    String details;
}
