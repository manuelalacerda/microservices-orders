package com.delivery.review.service;

import com.delivery.review.entity.ReviewSummary;
import com.delivery.review.repository.ReviewSummaryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.delivery.review.entity.ReviewSummary;
import com.delivery.review.repository.ReviewSummaryRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReviewConsumer {

    private final Map<Long, ReviewAccumulator> buffer = new ConcurrentHashMap<>();
    private final ReviewSummaryRepository repository;

    public ReviewConsumer(ReviewSummaryRepository repository) {
        this.repository = repository;
    }

    private static class ReviewAccumulator {
        private final Long dishId;
        private final String dishName;
        private double sumRatings;
        private long count;

        public ReviewAccumulator(Long dishId, String dishName, double rating, long count) {
            this.dishId = dishId;
            this.dishName = dishName;
            this.sumRatings = rating;
            this.count = count;
        }

        public void addRating(double rating) {
            this.sumRatings += rating;
            this.count++;
        }

        public Long getDishId() { return dishId; }
        public String getDishName() { return dishName; }
        public double getSumRatings() { return sumRatings; }
        public long getCount() { return count; }
    }

    public record ReviewMessage(Long dishId, String dishName, Integer rating, String comment) {}

    @RabbitListener(queues = "reviews.queue")
    public void receiveReview(ReviewMessage msg) {
        buffer.compute(msg.dishId(), (id, current) -> {
            if (current == null) {
                return new ReviewAccumulator(msg.dishId(), msg.dishName(), msg.rating(), 1);
            }
            current.addRating(msg.rating());
            return current;
        });
    }

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void flushToDatabase() {
        if (buffer.isEmpty()) return;

        Map<Long, ReviewAccumulator> snapshot = new HashMap<>(buffer);
        buffer.clear();

        for (ReviewAccumulator acc : snapshot.values()) {
            ReviewSummary summary = repository.findByDishId(acc.getDishId())
                    .orElse(new ReviewSummary(acc.getDishId(), acc.getDishName(), 0.0, 0L));

            long totalCount = summary.getCount() + acc.getCount();
            double totalSum = (summary.getAverage() * summary.getCount()) + acc.getSumRatings();
            double newAverage = totalSum / totalCount;

            summary.setAverage(Math.round(newAverage * 10.0) / 10.0);
            summary.setCount(totalCount);
            repository.save(summary);
        }
    }
}