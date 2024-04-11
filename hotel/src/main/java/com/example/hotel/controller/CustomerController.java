package com.example.hotel.controller;

import com.example.hotel.dto.Customer;
import com.example.hotel.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.ErrorManager;


import java.net.URLDecoder;
import java.net.URLEncoder;

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
                session.setAttribute("balance", loginCustomer.getBalance());
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

    @PostMapping(value = "/update")
    public String updateBalance(Customer customer, HttpSession session) {
        String customerId = (String) session.getAttribute("customer_id");


        try {
            if (customerId == null) {
                return "redirect:/login"; // 세션에 고객 ID가 없으면 로그인 페이지로 리다이렉션
            } else {
                customer.setCustomerId(customerId); // 세션에서 가져온 customerId를 설정
                customerService.updateBalance(customer); // 잔액 업데이트 서비스 호출
                int currentBalance = (int) session.getAttribute("balance");
                int updatedBalance = currentBalance + customer.getBalance();
                session.setAttribute("balance", updatedBalance);

                return "redirect:/"; // 성공적으로 업데이트된 경우 홈 페이지로 리다이렉션
            }
        } catch (Exception e) {
            // 예외가 발생한 경우 로깅하고 에러 페이지로 리다이렉션 또는 예외 메시지를 출력
            throw new RuntimeException(e);
        }
    }
}
