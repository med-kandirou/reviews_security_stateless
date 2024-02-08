package com.example.demo.controller;


import com.example.demo.Services.ReviewService;
import com.example.demo.model.Review;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ReviewService reviewService;

    public AdminController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}/delete")
    public void deleteReview(@PathVariable UUID id) {
        reviewService.deleteReview(id);
    }

    @PostMapping("/update")
    public Review updateReview(@RequestBody Review review) {
        return reviewService.updateReview(review);
    }

    @GetMapping("/reviewsReported")
    public List<Review> reviewsReported() {
        return reviewService.reviewsReported();
    }
}
