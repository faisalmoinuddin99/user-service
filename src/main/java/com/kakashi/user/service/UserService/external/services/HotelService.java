package com.kakashi.user.service.UserService.external.services;

import com.kakashi.user.service.UserService.dto.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/api/hotels/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId")  String hotelId);
}
