package com.delivery.payment.dto;

import java.math.BigDecimal;

public record PaymentRequest(BigDecimal amount, String paymentMethod) {}