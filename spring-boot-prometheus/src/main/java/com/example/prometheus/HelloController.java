package com.example.prometheus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/hello")
    public ResponseEntity<?> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return ResponseEntity.ok(String.format("Hello %s from Spring App %s", name, appName));
    }

}
