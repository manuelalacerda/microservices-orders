package com.delivery.review.repository;

import com.delivery.review.entity.ReviewSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReviewSummaryRepository extends JpaRepository<ReviewSummary, Long> {
    Optional<ReviewSummary> findByDishId(Long dishId);
    List<ReviewSummary> findAllByOrderByAverageDesc();
}