package com.delivery.order.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_orders")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long dishId;
    private int quantity;
    private BigDecimal totalPrice;
    private String status;
    private LocalDateTime createdAt;

    public CustomerOrder() {}
    public CustomerOrder(Long dishId, int quantity, BigDecimal totalPrice, String status) {
        this.dishId = dishId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Long getDishId() { return dishId; }
    public int getQuantity() { return quantity; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}