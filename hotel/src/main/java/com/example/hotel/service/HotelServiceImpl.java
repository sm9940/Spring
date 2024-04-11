package com.example.hotel.service;

import com.example.hotel.dao.HotelDao;
import com.example.hotel.dto.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{
    @Autowired
    HotelDao hotelDao;


    @Override
    public List<Hotel> getAllHotels() {
        return hotelDao.getAllHotels();
    }
}
