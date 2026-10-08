package com.delivery.order.config;

import com.delivery.order.entity.Dish;
import com.delivery.order.repository.DishRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(DishRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Dish("House Burger", "Brioche bun, 180g patty", new BigDecimal("39.90"), 10)); // Promoção
                repository.save(new Dish("Pizza Margherita", "Fresh mozzarella & basil", new BigDecimal("45.00"), 20));
                repository.save(new Dish("Vegan Bowl", "Quinoa, avocado & chickpeas", new BigDecimal("35.00"), 15));
                repository.save(new Dish("Pasta Carbonara", "Guanciale & pecorino", new BigDecimal("42.00"), 12));
                repository.save(new Dish("Sushi Combo", "16 pieces assortment", new BigDecimal("65.00"), 8));
            }
        };
    }
}