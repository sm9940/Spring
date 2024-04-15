package com.example.hotel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {
    private int payId;
    private String customerId;
    private String checkin;
    private String checkout;
    private String roomName;
    private String hotelName;
    private int price;
    private int payment;
    private int roomId;
    private Hotel hotel;
    private Room room;
    private Customer customer;


}
