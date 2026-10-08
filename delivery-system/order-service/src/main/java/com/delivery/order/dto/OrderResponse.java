package com.delivery.order.dto;

import java.math.BigDecimal;

public record OrderResponse(
        String orderId,
        String customerName,
        BigDecimal totalAmount,
        String orderStatus,
        PaymentResponse paymentDetails
) {}