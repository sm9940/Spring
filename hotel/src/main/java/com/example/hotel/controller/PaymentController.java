package com.example.hotel.controller;

import com.example.hotel.dto.Payment;
import com.example.hotel.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @PostMapping("/payment")
    public String processPayment(@RequestBody Payment payment) {
        paymentService.addPayment(payment);
        return "paymentComplete"; // or whatever page you want to redirect to after payment
    }
}
