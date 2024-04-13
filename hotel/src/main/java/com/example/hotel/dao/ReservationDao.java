package com.example.hotel.dao;

import com.example.hotel.dto.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface ReservationDao {
    void addReservation(Reservation reservation);
    void processPayment(Reservation reservation);
    Reservation getReservationById(int payId);
    List<Reservation> getAllReservations();
    void updateReservation(Reservation reservation);
    void deleteReservation(int payId);
    String showReservationPage(Model model, int hotelId);
}
