package com.example.hotel.dao;

import com.example.hotel.dto.Hotel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelDao {
    public List<Hotel> getAllHotels();
}
