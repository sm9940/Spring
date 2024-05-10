package com.hospital.dto;

import com.hospital.constant.RStatus;
import com.hospital.entity.Doctor;
import com.hospital.entity.DoctorImg;
import com.hospital.entity.Reservation;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class ReservationDto {
    private Long id;
    private Long memberId;
    private Long doctorId;
    private String rDate;
    private String rTime;
    private RStatus rStatus;

    private static ModelMapper modelMapper =new ModelMapper();
    public ReservationDto() {
        // 하드코딩된 값 설정
        this.rStatus = RStatus.RESERVATION;
    }

    public Reservation createReservation(){
        Reservation reservation = modelMapper.map(this,Reservation.class);
        return reservation;
    }

    public static ReservationDto of(Reservation reservation){
        return modelMapper.map(reservation,ReservationDto.class);
    }
}
