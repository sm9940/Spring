package com.example.hotel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
    private int paymentId;
    private int payId;
    private int amount;
}
