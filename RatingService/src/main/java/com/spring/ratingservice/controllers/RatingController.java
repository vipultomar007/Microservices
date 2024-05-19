package com.spring.ratingservice.controllers;


import com.spring.ratingservice.entities.Rating;
import com.spring.ratingservice.serices.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    //create

    @PostMapping
    public Rating createRating(@RequestBody  Rating rating) {
        return ratingService.createRating(rating);
    }

    //get all
    @GetMapping("/getAllRatings")
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    //get user
    @GetMapping("/users/{userId}")
    public List<Rating> getRatingsByUserId(@PathVariable String userId) {
        return ratingService.getRatingsByUserId(userId);
    }

    //get hotel
    @GetMapping("/hotels/{hotelId}")
    public List<Rating> getRatingsByHotelId(@PathVariable String hotelId) {
        return ratingService.getRatingsByHotelId(hotelId);
    }
}
