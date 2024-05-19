package com.spring.userservice.external.services;


import com.spring.userservice.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {

    @GetMapping("/hotels/getHotel/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
