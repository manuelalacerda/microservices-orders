package com.delivery.order.dto;

import java.math.BigDecimal;

public record OrderRequest(String customerName, BigDecimal totalAmount, String paymentMethod) {}