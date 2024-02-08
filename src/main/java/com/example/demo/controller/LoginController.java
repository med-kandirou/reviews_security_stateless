package com.example.demo.controller;


import com.example.demo.Services.ReviewService;
import com.example.demo.model.Review;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/Auth")
public class LoginController {

    private final ReviewService reviewService;
    public LoginController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }
}

