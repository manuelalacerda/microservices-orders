package com.delivery.order.client;

import com.delivery.order.dto.PaymentRequest;
import com.delivery.order.dto.PaymentResponse;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class PaymentClient {

    private final RestTemplate restTemplate;

    public PaymentClient(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retryable(
            retryFor = Exception.class,
            maxAttempts = 4,
            backoff = @Backoff(delay = 200, multiplier = 2.0, maxDelay = 2000, random = true)
    )
    public PaymentResponse processPayment(PaymentRequest request) {
        return restTemplate.postForObject(
                "http://PAYMENT-SERVICE/payments",
                request,
                PaymentResponse.class
        );
    }
}