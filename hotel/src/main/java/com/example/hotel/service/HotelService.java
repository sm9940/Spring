package com.example.hotel.service;

import com.example.hotel.dto.Hotel;
import com.example.hotel.dto.Room;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HotelService {
  public  List<Hotel> getAllHotels();
    public List<Room>getAllHotelswithRooms();


}
