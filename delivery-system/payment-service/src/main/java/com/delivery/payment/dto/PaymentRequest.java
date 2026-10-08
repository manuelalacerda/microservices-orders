package com.delivery.payment.dto;

import java.math.BigDecimal;

public class PaymentRequest {
    private BigDecimal amount;
    private String status;

    public PaymentRequest(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentRequest(BigDecimal amount, String status) {
        this.amount = amount;
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}