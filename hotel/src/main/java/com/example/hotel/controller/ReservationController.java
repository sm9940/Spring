package com.example.hotel.controller;

import com.example.hotel.dao.ReservationDao;
import com.example.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReservationController {
    @Autowired
    ReservationService reservationService;


}
