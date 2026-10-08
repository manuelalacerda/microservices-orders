package com.delivery.order.dto;

import java.math.BigDecimal;

public record PaymentRequest(BigDecimal amount, String paymentMethod) {}