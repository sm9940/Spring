package com.example.hotel.controller;

import com.example.hotel.dto.Hotel;
import com.example.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping(value = "/hotels/rooms")
    public String hotel(){
        return "/hotels/rooms";
    }
}
