package com.spring.userservice.controllers;


import com.spring.userservice.entities.User;
import com.spring.userservice.services.UserService;
import com.spring.userservice.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    //create

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    //getAll

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    //get user by id
    int retrycount  =1;
    @GetMapping("/getUser/{id}")
//    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public User getUserById(@PathVariable String id) {
        logger.info("retry"+retrycount);
        retrycount++;
        return userService.getUser(id);
    }

    //creating fallback

    public User ratingHotelFallback(@PathVariable String id, Exception ex) {
        logger.info("fallback method called : "+ex.getMessage());
        User user = User.builder()
                .email("dummy@email.com")
                .name("Dummy User")
                .about("This is a dummy user because services are down")
                .userId("454844")
                .build();
        return user;
    }

    //delete

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "User deleted";
    }
}
