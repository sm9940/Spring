package com.example.hotel.service;

import com.example.hotel.dao.ReservationDao;
import com.example.hotel.dto.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    ReservationDao reservationDao;
    @Override
    public void addReservation(Reservation reservation) {
            reservationDao.addReservation(reservation);
    }

    @Override
    public Reservation getReservationById(int payId) {
        return reservationDao.getReservationById(payId);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationDao.getAllReservations();
    }

    @Override
    public void updateReservation(Reservation reservation) {
            reservationDao.updateReservation(reservation);
    }

    @Override
    public void deleteReservation(int payId) {
            reservationDao.deleteReservation(payId);
    }
}
