package com.delivery.review.controller;

import com.delivery.review.entity.ReviewSummary;
import com.delivery.review.repository.ReviewSummaryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewSummaryRepository repository;

    public ReviewController(ReviewSummaryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/ranking")
    public List<ReviewSummary> getRanking() {
        return repository.findAllByOrderByAverageDesc();
    }
}