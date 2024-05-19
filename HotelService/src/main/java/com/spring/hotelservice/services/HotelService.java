package com.spring.hotelservice.services;


import com.spring.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel getHotelById(String id);

    List<Hotel> getAllHotels();

    Hotel addHotel(Hotel hotel);
}
