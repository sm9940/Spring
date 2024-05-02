package com.hospital.dto;

import com.hospital.entity.DoctorImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class DoctorImgDto {
    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;

    private static ModelMapper modelMapper=new ModelMapper();

    public static DoctorImgDto of(DoctorImg doctorImg){
        return modelMapper.map(doctorImg, DoctorImgDto.class);
    }
}
