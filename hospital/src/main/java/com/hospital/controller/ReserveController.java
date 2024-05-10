package com.hospital.controller;

import com.hospital.dto.ReservationDto;
import com.hospital.dto.ReserveHistDto;
import com.hospital.entity.Reservation;
import com.hospital.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

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

    //주문 내역 페이지
    @GetMapping(value = {"/reserves","/reserves/{page}"})
    public String reserveHist(@PathVariable("page") Optional<Integer> page, Principal principal,
                            Model model){
        //한 페이지당 4개의 게시물 보여줌
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,4);

        Page<ReserveHistDto> reserveHistDtoList = reservationService.getReserveList(principal.getName(),pageable);

        model.addAttribute("reserves",reserveHistDtoList);
        model.addAttribute("maxPage",5);
        return "reserve/reserveHist";
    }
    @DeleteMapping("/reserves/{rId}/delete")
    public @ResponseBody ResponseEntity deleteReservation(@PathVariable("rId") Long rId, Principal principal){


        reservationService.deleteReservation(rId);

        return new ResponseEntity<Long>(rId,HttpStatus.OK);
    }

    }
