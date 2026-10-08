package com.delivery.order.dto;

import java.math.BigDecimal;

public record OrderRequest(
        Long dishId,
        Integer quantity,
        BigDecimal totalAmount,
        String paymentMethod,
        String customerName
) {}