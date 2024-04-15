package com.example.hotel.service;


import com.example.hotel.dao.ReservationDao;
import com.example.hotel.dto.Reservation;
import com.example.hotel.dto.Room;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationDao reservationDao;

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



    @Override
    public int calculateNumberOfDays(String checkin, String checkout) {
        // 문자열로된 날짜를 LocalDate 객체로 변환
        LocalDate checkinDate = LocalDate.parse(checkin);
        LocalDate checkoutDate = LocalDate.parse(checkout);

        // 날짜 간의 일 수 계산
        long numberOfDays = ChronoUnit.DAYS.between(checkinDate, checkoutDate);

        // 계산 결과 반환 (int 형으로 변환)
        return (int) numberOfDays;
    }

    @Override
    public void editReservation(Reservation reservation) {
        reservationDao.editReservation(reservation);
    }

    @Override
    public Room selectRoomByRoomId(int roomId) {
        return reservationDao.selectRoomByRoomId(roomId);
    }

    @Override
    public Reservation getReadReservation(int payId) {
        return reservationDao.getReadReservation(payId);
    }


}

