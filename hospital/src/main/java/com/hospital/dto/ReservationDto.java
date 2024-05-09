package com.hospital.dto;

import com.hospital.entity.Doctor;
import com.hospital.entity.DoctorImg;
import com.hospital.entity.Reservation;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ReservationDto {
    private Long memberId;
    private Long doctorId;
    private String rDate;
    private String rTime;

    public static ReservationDto createReservation(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setMemberId(reservation.getMember().getId());
        reservationDto.setDoctorId(reservation.getDoctor().getId());
        reservationDto.setRDate(reservation.getRDate().toString());
        reservationDto.setRTime(reservation.getRTime().toString());
        return reservationDto;
    }
}
