package com.example.socket.io.data;

import lombok.Data;

@Data
public class Message {

    private String senderName;
    private String targetUserName;
    private String message;
}
