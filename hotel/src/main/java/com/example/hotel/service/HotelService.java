package com.example.hotel.service;

import com.example.hotel.dto.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HotelService {
    List<Hotel> getAllHotels();
}
