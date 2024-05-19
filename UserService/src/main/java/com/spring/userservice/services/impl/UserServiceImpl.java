package com.spring.userservice.services.impl;

import com.spring.userservice.entities.Hotel;
import com.spring.userservice.entities.Rating;
import com.spring.userservice.entities.User;
import com.spring.userservice.exceptions.ResourceNotFoundException;
import com.spring.userservice.external.services.HotelService;
import com.spring.userservice.repositories.UserRepository;
import com.spring.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private HotelService hotelService;

    private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public User saveUser(User user) {
        String uuid = UUID.randomUUID().toString();
        user.setUserId(uuid);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
        //fetch rating from Rating service
        Rating[] ratings = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info(ratings.toString());
        List<Rating> ratingList = Arrays.stream(ratings).toList();

        List<Rating> ratingsList = ratingList.stream().map(rating -> {
            //api call to hotel service
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/getHotel/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            Hotel hotel = forEntity.getBody();
//            logger.info("response status: " + forEntity.getStatusCode());

            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingsList);
        return user;
    }

    @Override
    public void deleteUser(String userId) {

    }
}
