package com.example.hotel.controller;

import com.example.hotel.dto.Hotel;
import com.example.hotel.dto.Reservation;
import com.example.hotel.dto.Room;
import com.example.hotel.service.HotelService;
import com.example.hotel.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private HotelService hotelService;

    @GetMapping(value ="/reservation/form")
    public String showReservationForm(Model model, HttpSession session) {
        String customerId = (String) session.getAttribute("customer_id");


        if (customerId == null) {
            // 세션에서 customerId가 없을 경우, 로그인 페이지로 리다이렉트합니다.
            return "redirect:/";
        }

        model.addAttribute("customerId", customerId);
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);

        return "/reservation/reservation_form";
    }

    @GetMapping("/rooms")
    public String getRoomsByHotel(@RequestParam("hotelId") int hotelId, Model model) {
        List<Room> rooms = hotelService.getRoomsByHotelId(hotelId);
        model.addAttribute("rooms", rooms);
        return "reservation/room_list";
    }

    @PostMapping("/reservation")
    public String processReservation(Reservation reservation, Model model, HttpServletRequest request) {
        String customerId = (String) request.getSession().getAttribute("customer_id");
        reservation.setCustomerId(customerId);
        Room room = reservation.getRoom();
        reservation.setRoom(room);
        reservationService.addReservation(reservation);
        model.addAttribute("reservation", reservation);
        return "/reservation/reservationComplete";
    }
    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
    public String list(HttpSession session, HttpServletRequest request, Model model) {
        // 여기에 예약 목록을 가져오는 로직을 추가하세요
        // 이 예시에서는 서비스 레이어를 사용하여 예약 목록을 가져오는 것으로 가정합니다.
        // ReservationService reservationService;
        List<Reservation> reservations = reservationService.getAllReservations();

        // 모델에 예약 목록을 추가합니다.
         model.addAttribute("reservations", reservations);

        // reservation_list.html로 이동합니다.
        return "/reservation/reservation_list";
    }




}
