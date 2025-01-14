package com.example.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    // Konfigurasi message broker
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Pesan dari server akan dikirim ke endpoint "/topic"
        config.enableSimpleBroker("/topic");
        // Prefix untuk semua message yang diterima dari client
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Menentukan endpoint WebSocket yang dapat diakses oleh klien
        registry.addEndpoint("/ws").withSockJS();
    }
}
