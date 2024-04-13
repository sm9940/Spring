package com.example.hotel.controller;

import com.example.hotel.dto.Reservation;
import com.example.hotel.service.ReservationService;
import org.springframework.ui.Model; // 올바른 import를 사용해야 합니다.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.hotel.dto.Hotel;
import com.example.hotel.service.HotelService;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    HotelService hotelService;


    @GetMapping("/reservation")
    public String showReservationPage(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        return "reservation/reservation";
    }

    @PostMapping("/reservation")
    public String processPayment(Reservation reservation) {

        reservationService.processPayment(reservation);
        return "redirect:/confirmation";
    }
}
