package com.delivery.order.dto;

import java.math.BigDecimal;

public record PaymentResponse(String paymentId, String status, BigDecimal amount) {}