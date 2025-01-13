package com.example.activemq.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
    private String name;
    private String email;
    private String website;
}
