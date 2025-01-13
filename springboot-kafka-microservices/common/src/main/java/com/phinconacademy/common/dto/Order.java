package com.phinconacademy.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    Long orderId;
    Long productId;
    Integer quantity;
    Double price;
    String customerId;
}
