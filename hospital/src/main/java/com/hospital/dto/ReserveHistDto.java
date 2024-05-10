package com.hospital.dto;

import com.hospital.constant.RStatus;
import com.hospital.entity.Reservation;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReserveHistDto {
    public ReserveHistDto (Reservation reservation){
        this.rId = reservation.getId();
        this.memberId=reservation.getMember().getId();
        this.doctorId=reservation.getDoctor().getId();
        this.doctorNm=reservation.getDoctor().getDoctorNm();
        this.rDate=reservation.getRDate();
        this.rTime=reservation.getRTime();
        this.rStatus=reservation.getRStatus();
    }

    private Long rId;
    private Long memberId;
    private Long doctorId;
    private String doctorNm;
    private String rDate;
    private String rTime;
    private RStatus rStatus;

    private List<ReservationDto> reservationDtoList =new ArrayList<>();

    public void addReservationDto(ReservationDto reservationDto){
        this.reservationDtoList.add(reservationDto);
    }
}
