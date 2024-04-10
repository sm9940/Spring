package com.example.hotel.controller;

import com.example.hotel.dto.Customer;
import com.example.hotel.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value="/login")
    public String login(){return "customer/login";}

    @PostMapping(value = "/login")
    public String loginCustomer(Customer customer, HttpSession session){
        try {
            Customer loginCustomer = customerService.loginCustomer(customer);
            if(loginCustomer != null){
                session.setAttribute("name", loginCustomer.getName());
                session.setAttribute("customer_id",loginCustomer.getCustomerId());
                return "redirect:/";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "customer/login";
    }
    @GetMapping(value = "/logout")
    public String logoutCustomer(HttpSession session) {
        session.removeAttribute("name");
        session.removeAttribute("customer_id");
        return  "redirect:/";
    }
}
