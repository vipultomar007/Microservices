package com.spring.hotelservice.services.impl;

import com.spring.hotelservice.entities.Hotel;
import com.spring.hotelservice.exception.ResourceNotFoundException;
import com.spring.hotelservice.repositories.HotelRespository;
import com.spring.hotelservice.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRespository respository;

    @Override
    public Hotel getHotelById(String id) {
        return respository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found"));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return respository.findAll();
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        String uuid = UUID.randomUUID().toString();
        hotel.setId(uuid);
        return respository.save(hotel);
    }
}
