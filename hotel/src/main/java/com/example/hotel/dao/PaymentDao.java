package com.example.hotel.dao;

import com.example.hotel.dto.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {
    void addPayment(Payment payment);
}
