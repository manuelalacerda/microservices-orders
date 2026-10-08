package com.delivery.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReviewSummary {

    @Id
    private Long dishId;
    private String dishName;
    private Double average;
    private Long count;

    public ReviewSummary() {}

    public ReviewSummary(Long dishId, String dishName, Double average, Long count) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.average = average;
        this.count = count;
    }

    public Long getDishId() { return dishId; }
    public void setDishId(Long dishId) { this.dishId = dishId; }

    public String getDishName() { return dishName; }
    public void setDishName(String dishName) { this.dishName = dishName; }

    public Double getAverage() { return average; }
    public void setAverage(Double average) { this.average = average; }

    public Long getCount() { return count; }
    public void setCount(Long count) { this.count = count; }
}