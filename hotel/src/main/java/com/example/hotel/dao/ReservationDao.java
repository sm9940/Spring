package com.example.hotel.dao;

import com.example.hotel.dto.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationDao {
   public void addReservation(Reservation reservation);
   public Reservation getReservationById(int payId);
    public List<Reservation> getAllReservations();
    public void updateReservation(Reservation reservation);
    public void deleteReservation(int payId);


}
