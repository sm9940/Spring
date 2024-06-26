package com.example.hotel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room {
    private int roomId;
    private  String roomName;
    private  int price;
    private String roomImg;
    private Hotel hotel;
    private int hotelId;
}
