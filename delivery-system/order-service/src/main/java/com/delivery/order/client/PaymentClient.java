package com.delivery.order.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class PaymentClient {

    @Autowired
    private RestTemplate restTemplate;

    @Retryable(
            retryFor = { Exception.class },
            maxAttempts = 4,
            backoff = @Backoff(delay = 500, multiplier = 2.0, maxDelay = 3000, random = true)
    )
    public Map<String, Object> processPayment(BigDecimal amount) {
        Map<String, Object> request = Map.of("amount", amount);
        return restTemplate.postForObject("http://PAYMENT-SERVICE/payments", request, Map.class);
    }
}