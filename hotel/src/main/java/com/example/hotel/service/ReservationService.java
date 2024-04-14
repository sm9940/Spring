package com.example.hotel.service;



import com.example.hotel.dto.Reservation;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import java.util.List;

public interface ReservationService {
    void addReservation(Reservation reservation);


    Reservation getReservationById(int payId);


    List<Reservation> getAllReservations();





    void updateReservation(Reservation reservation);


    void deleteReservation(int payId);

    public int calculateNumberOfDays(String checkin, String checkout);
}
