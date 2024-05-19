package com.spring.hotelservice.repositories;

import com.spring.hotelservice.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRespository extends JpaRepository<Hotel, String> {
}
