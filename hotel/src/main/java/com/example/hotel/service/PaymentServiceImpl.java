package com.example.hotel.service;

import com.example.hotel.dao.PaymentDao;
import com.example.hotel.dto.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    PaymentDao paymentDao;
    @Override
    public void addPayment(Payment payment) {
        paymentDao.addPayment(payment);
    }
}
