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
        return doctor;
    }

    public static DoctorFormDto of(Doctor doctor){
        return modelMapper.map(doctor, DoctorFormDto.class);
    }
}
