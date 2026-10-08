package com.delivery.payment.dto;

import java.math.BigDecimal;

public record PaymentResponse(String paymentId, String status, BigDecimal amount) {}