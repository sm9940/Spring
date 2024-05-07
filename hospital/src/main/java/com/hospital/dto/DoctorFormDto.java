package com.hospital.dto;

import com.hospital.constant.Day;
import com.hospital.constant.Major;
import com.hospital.entity.AvailableDay;
import com.hospital.entity.AvailableTime;
import com.hospital.entity.Doctor;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DoctorFormDto {
    private Long id;
    @NotBlank(message = "교수명을 입력해주세요")
    private String doctorNm;
    @NotBlank(message = "교수 진료내용을 입력해주세요")
    private String doctorDetail;
    private Time startTime;
    private Time endTime;
    private Major major;
    private Day day;

    private List<AvailableDayDto> availableDayDtoList = new ArrayList<>();
    private List<AvailableTimeDto> availableTimeDtoList = new ArrayList<>();
    private List<DoctorImgDto> doctorImgDtoList = new ArrayList<>();
    private List<Long> doctorImgids = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Doctor createDoctor(){
        Doctor doctor = modelMapper.map(this, Doctor.class);

        // availableDayDtoList에서 AvailableDay 엔티티로의 매핑
        List<AvailableDay> availableDays = new ArrayList<>();
        for (AvailableDayDto availableDayDto : this.availableDayDtoList) {
            availableDays.add(modelMapper.map(availableDayDto, AvailableDay.class));

            // AvailableDay에 속한 AvailableTimeDto 리스트를 가져와서 AvailableTime 엔티티로 매핑
            List<AvailableTime> availableTimes = new ArrayList<>();
            for (AvailableTimeDto availableTimeDto : availableDayDto.getAvailableTimeDtoList()) {
                availableTimes.add(modelMapper.map(availableTimeDto, AvailableTime.class));
            }
            // AvailableTime 엔티티를 AvailableDay에 설정
            availableDays.get(availableDays.size() - 1).setAvailableTimes(availableTimes);
        }
        doctor.setAvailableDays(availableDays);

        return doctor;
    }

    public static DoctorFormDto of(Doctor doctor){
        return modelMapper.map(doctor, DoctorFormDto.class);
    }
}
