package com.example.hotel.dao;

import com.example.hotel.dto.Reservation;
import com.example.hotel.dto.Room;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReservationDao {

    void addReservation(Reservation reservation);


    Reservation getReservationById(int payId);


    List<Reservation> getAllReservations();

    public List<Reservation> getReservationsWithPaging(Map map);


    public int calculateNumberOfDays(String checkin, String checkout);
    void updateReservation(Reservation reservation);


    void deleteReservation(int payId);

    int getRoomPriceByRoomId(int roomId);
    int payment(int payId);


    void editReservation(Reservation reservation);
    Room selectRoomByRoomId(int roomId);
    Reservation getReadReservation(int payId);
}
