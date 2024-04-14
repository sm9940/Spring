package com.example.hotel.dao;

import com.example.hotel.dto.Reservation;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface ReservationDao {

    void addReservation(Reservation reservation);


    Reservation getReservationById(int payId);


    List<Reservation> getAllReservations();




    public int calculateNumberOfDays(String checkin, String checkout);
    void updateReservation(Reservation reservation);


    void deleteReservation(int payId);


}
