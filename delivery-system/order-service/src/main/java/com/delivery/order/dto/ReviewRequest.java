package com.delivery.order.dto;

public record ReviewRequest(
        Long dishId,
        String dishName,
        Integer rating,
        String comment
) {}