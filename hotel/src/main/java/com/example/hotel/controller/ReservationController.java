package com.example.hotel.controller;

import com.example.hotel.dao.ReservationDao;
import com.example.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservation")
    public String showReservationPage() {
        return "reservation/reservation"; // 해당하는 HTML 파일의 이름 (예: reservation.html)
    }
}
