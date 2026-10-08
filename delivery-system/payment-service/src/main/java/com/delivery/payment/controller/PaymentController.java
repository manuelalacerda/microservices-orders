package com.delivery.payment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Value("${server.port:8081}")
    private String serverPort;

    private final Random random = new Random();

    @PostMapping
    public ResponseEntity<?> processPayment(@RequestBody Map<String, Object> request) {
        if (random.nextBoolean()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Simulated payment failure"));
        }

        return ResponseEntity.ok(Map.of(
                "status", "APPROVED",
                "instance", Integer.parseInt(serverPort)
        ));
    }
}