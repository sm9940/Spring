package com.hospital.dto;

import com.hospital.constant.RStatus;
import com.hospital.entity.Doctor;
import com.hospital.entity.DoctorImg;
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
        Doctor doctor = reservation.getDoctor();

        // 의사 객체가 null이 아닌 경우에만 이미지들을 가져와서 설정합니다.
        if (doctor != null) {
            List<DoctorImg> doctorImgs = doctor.getDoctorImgs();

            // doctorImgs가 null이 아니고 비어있지 않은 경우에만 설정합니다.
            if (doctorImgs != null && !doctorImgs.isEmpty()) {
                // 여러 이미지 중 첫 번째 이미지를 설정합니다. 필요에 따라 다양한 방법으로 처리할 수 있습니다.
                this.imgUrl = doctorImgs.get(0).getImgUrl();
            }
        }
    }

    private Long rId;
    private Long memberId;
    private Long doctorId;
    private String doctorNm;
    private String rDate;
    private String rTime;
    private RStatus rStatus;
    private String imgUrl;

    private List<ReservationDto> reservationDtoList =new ArrayList<>();

    public void addReservationDto(ReservationDto reservationDto){
        this.reservationDtoList.add(reservationDto);
    }
}
