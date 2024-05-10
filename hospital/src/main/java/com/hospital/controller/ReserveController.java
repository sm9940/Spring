package com.hospital.controller;

import com.hospital.dto.ReservationDto;
import com.hospital.entity.Reservation;
import com.hospital.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ReserveController {
    private final ReservationService reservationService;

    @PostMapping(value = "/reserve")
    public String addReserve(@Valid ReservationDto reservationDto, Model model) {
        try {
            reservationService.createReservation(reservationDto);

            return "redirect:/";
        } catch (RuntimeException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "doctor/doctorDtl";
        }
    }
    }
