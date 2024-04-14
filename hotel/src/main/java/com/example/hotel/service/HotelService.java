package com.example.hotel.service;

import com.example.hotel.dto.Hotel;
import com.example.hotel.dto.Room;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;


public interface HotelService {
  public  List<Hotel> getAllHotels();
    public List<Room>getAllHotelswithRooms();
  Hotel getHotelById(int hotelId);
  List<Room> getRoomsByHotelId(int hotelId);
  String getRoomNameByRoomId(int roomId);

}
