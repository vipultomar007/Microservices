package com.spring.ratingservice.serices;

import com.spring.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //get all ratings
    List<Rating> getAllRatings();

    //get all by UserId
    List<Rating> getRatingsByUserId(String userId);

    //get All by hotel
    List<Rating> getRatingsByHotelId(String hotelId);
}
