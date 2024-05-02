package com.hospital.dto;

import com.hospital.constant.Major;
import com.hospital.entity.Doctor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DoctorFormDto {
    private Long id;

    private String doctorNm;
    private String a;
    private Major major;

    private List<DoctorImgDto> doctorImgDtoList = new ArrayList<>();
    private List<Long> doctorImgids = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Doctor createDoctor(){
        return modelMapper.map(this,Doctor.class);
    }
    public static DoctorFormDto of(Doctor doctor){
        return modelMapper.map(doctor, DoctorFormDto.class);
    }
}
