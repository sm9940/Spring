package com.example.hotel.dao;

import com.example.hotel.dto.Hotel;
import com.example.hotel.dto.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelDao {
    public List<Hotel> getAllHotels();

    public List<Room>getAllHotelswithRooms();
    Hotel getHotelById(int hotelId);
    List<Room> getRoomsByHotelId(int hotelId);

}
