package com.phinconacademy.common.dto;

import com.phinconacademy.common.status.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    OrderStatus status;
    String message;
    Order order;
}
