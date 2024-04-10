package com.example.hotel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private String customerId;
    private  String email;
    private  String password;
    private  String name;

    private  String phone;
    private String address;
    private int balance;


}
