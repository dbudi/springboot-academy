package com.example.rabbitmq.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {
    String name;
    Integer age;
    String branch;
}
