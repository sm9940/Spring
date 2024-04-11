package com.example.hotel.controller;

import com.example.hotel.dto.Hotel;
import com.example.hotel.dto.Room;
import com.example.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/")
    public String getAllHotels(Model model) {
        // 호텔 데이터를 가져와서 모델에 추가
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);

        // hotel.html 등의 템플릿을 렌더링하고 모델 데이터 전달
        return "index"; // 해당 HTML 파일 이름
    }

    @GetMapping("/hotelDetails")
    public String hotelDetails(@RequestParam("hotelId") int hotelId,Model model) {

        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        List<Room>rooms= hotelService.getAllHotelswithRooms(); // 호텔 정보와 방 정보를 포함한 리스트 가져오기
        model.addAttribute("rooms", rooms);


        return "hotels/hotelDetails";
    }
}

