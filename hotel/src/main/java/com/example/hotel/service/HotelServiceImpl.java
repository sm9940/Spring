package com.example.hotel.service;

import com.example.hotel.dao.HotelDao;
import com.example.hotel.dto.Hotel;
import com.example.hotel.dto.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{
    @Autowired
    HotelDao hotelDao;


    @Override
    public List<Hotel> getAllHotels() {
        return hotelDao.getAllHotels();
    }

    @Override
    public List<Room> getAllHotelswithRooms() {
        return hotelDao.getAllHotelswithRooms();
    }

    @Override
    public Hotel getHotelById(int hotelId) {
        return hotelDao.getHotelById(hotelId);
    }

    @Override
    public List<Room> getRoomsByHotelId(int hotelId) {
        return hotelDao.getRoomsByHotelId(hotelId);
    }


}
