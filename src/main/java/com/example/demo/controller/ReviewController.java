package com.example.demo.controller;


import com.example.demo.Services.ReviewService;
import com.example.demo.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/create")
    public String createReview(@RequestBody Review review) {
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }


    @GetMapping("/{id}")
    public Review showReviewDetails(@PathVariable UUID id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping
    public List<Review> showAllReviews() {
        return reviewService.getAllReviews();
    }


    @PostMapping("/moderator/{idReview}/claim")
    public Review claimReview(@PathVariable UUID idReview) {
        return reviewService.claimReview(idReview);
    }


}

