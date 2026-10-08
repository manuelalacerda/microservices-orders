package com.delivery.order.dto;

public record ReviewRequest(
        Long dishId,
        Integer rating,
        String comment
) {}