package com.example.hotel.service;

import com.example.hotel.dto.Reservation;

import java.util.List;

public interface ReservationService {
    public void addReservation(Reservation reservation);
    public Reservation getReservationById(int payId);
    List<Reservation> getAllReservations();
    void updateReservation(Reservation reservation);
    void deleteReservation(int payId);
}
