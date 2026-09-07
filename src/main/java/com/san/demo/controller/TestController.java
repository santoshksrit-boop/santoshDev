package com.san.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/ping")
    public Map<String, Object> ping() {
        return Map.of(
                "status", "Welcome Rahim",
                "timestamp", Instant.now().toString()
        );
    }

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return Map.of(
                "message", "Hello from demo server",
                "timestamp", Instant.now().toString()
        );
    }

    @PostMapping("/echo")
    public Map<String, Object> echo(@RequestBody(required = false) Map<String, Object> body) {
        return Map.of(
                "received", body == null ? Map.of() : body,
                "timestamp", Instant.now().toString()
        );
    }
}
