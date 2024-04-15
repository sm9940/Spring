package com.example.hotel.controller;

import com.example.hotel.dto.Hotel;
import com.example.hotel.dto.Reservation;
import com.example.hotel.dto.Room;
import com.example.hotel.service.HotelService;
import com.example.hotel.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private HotelService hotelService;

    @GetMapping("/rooms")
    public String getRoomsByHotel(@RequestParam("hotelId") int hotelId, Model model) {

      List<Room> rooms= hotelService.getRoomsByHotelId(hotelId);
        model.addAttribute("rooms", rooms);
        return "reservation/room_list";
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(HttpSession session, HttpServletRequest request, Model model) {
        try {
            String customerId = (String) session.getAttribute("customer_id");
            List<Reservation> reservations = reservationService.getAllReservations();

            model.addAttribute("reservations", reservations); // DB에서 가져온 전체 예약 리스트


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "/reservation/reservation_list";
    }

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
    @RequestMapping(value = "/reservation", method = {RequestMethod.GET, RequestMethod.POST})
    public String processReservation(Reservation reservation, Model model, HttpSession session) {
        String customerId = (String) session.getAttribute("customer_id");
        reservation.setCustomerId(customerId);

        Room room = reservationService.selectRoomByRoomId(reservation.getRoomId()); // room 가져오기
        if (room != null) {
            // room이 null이 아닐 경우에만 가격 설정
            int price = room.getPrice();
            reservation.setPrice(price); // 예약 객체에 가격 설정

            reservationService.addReservation(reservation);
            model.addAttribute("reservation", reservation);
            return "/reservation/reservationComplete";
        } else {
            // room이 null인 경우 처리
            // 어떤 처리를 할지에 따라 로직 추가
            return "redirect:/"; // 예시로 메인 페이지로 리다이렉트
        }
    }

    @GetMapping("/view/{payId}")
    public String view(@PathVariable("payId") int payId, HttpServletRequest request, Model model) {
        // 예약 ID를 사용하여 해당 예약의 상세 정보를 조회합니다.
        Reservation reservation = reservationService.getReservationById(payId);

        // 조회된 예약이 없을 경우 처리
        if (reservation == null) {
            // 예약이 없다면 어떤 작업을 할지에 대한 로직을 추가하세요.
            return "redirect:/"; // 예시로 메인 페이지로 리다이렉트합니다.
        }

        // 조회된 예약 정보를 모델에 추가하여 Thymeleaf 템플릿에 전달합니다.
        model.addAttribute("reservation", reservation);

        // 예약 상세 정보 페이지로 이동합니다.
        return "reservation/view";
    }
    @DeleteMapping(value = "/delete/{payId}")
    public @ResponseBody ResponseEntity deleteReservation(@PathVariable("payId") int payId, HttpSession session) {
        try {

            Object customerId = session.getAttribute("customer_id");

            if(customerId == null) {
                return new ResponseEntity<String>("삭제 실패. 관리자에게 문의하세요.", HttpStatus.UNAUTHORIZED);
            } else {
                reservationService.deleteReservation(payId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("삭제 실패. 관리자에게 문의하세요.", HttpStatus.BAD_REQUEST);
        }
        //ResponseEntity<첫번째 매개변수의 타입>(result결과, response상태코드)
        //HttpsStatus.OK 일때는 ajax의 success함수로 결과가 출력된다.
        return new ResponseEntity<Integer>(payId, HttpStatus.OK );
    }



    @GetMapping("/edit/{payId}")
    public String editReservation(@PathVariable("payId") int payId, Model model) {
        // 예약을 수정하는 페이지로 이동합니다.
        Reservation reservation = reservationService.getReservationById(payId);
        model.addAttribute("reservation", reservation);
        return "reservation/edit"; // 수정 페이지로 이동합니다.
    }

    @PostMapping("/update/edit")
    public String updateReservation(@ModelAttribute("reservation") Reservation reservation, HttpSession session) {
        String customerId = (String) session.getAttribute("customer_id");
        if (customerId == null) {
            // 세션에 사용자 ID가 없으면 로그인 페이지로 리다이렉트합니다.
            return "redirect:/login";
        }

        // 예약을 업데이트합니다.
        reservationService.updateReservation(reservation);

        // 수정이 완료되면 해당 예약의 상세 정보 페이지로 리다이렉트합니다.
        return "redirect:/view/" + reservation.getPayId();
    }

}
