package com.delivery.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_payments")
public class PaymentEntity {

    @Id
    private String id;
    private String status;
    private BigDecimal amount;

    public PaymentEntity() {}

    public PaymentEntity(String id, String status, BigDecimal amount) {
        this.id = id;
        this.status = status;
        this.amount = amount;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}