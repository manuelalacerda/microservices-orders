package com.delivery.review.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/status")
    public String getStatus() {
        return "Review Service a rodar na porta: " + port;
    }
}