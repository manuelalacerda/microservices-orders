package com.delivery.payment.controller;

import com.delivery.payment.dto.PaymentRequest;
import com.delivery.payment.dto.PaymentResponse;
import com.delivery.payment.entity.PaymentEntity;
import com.delivery.payment.repository.PaymentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/process")
    public PaymentResponse processPayment(@RequestBody PaymentRequest request) {
        String paymentId = UUID.randomUUID().toString();
        String status = "APPROVED";

        PaymentEntity entity = new PaymentEntity(paymentId, status, request.amount());
        paymentRepository.save(entity);

        return new PaymentResponse(paymentId, status, request.amount());
    }
}