package com.spring.userservice.external.services;

import com.spring.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {
    //post

    @PostMapping("/ratings")
    public Rating createRating(Rating rating);
}
