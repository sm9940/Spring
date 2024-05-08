package com.hospital.dto;

import com.hospital.constant.Major;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainDoctorDto {
    private Long id;
    private String doctorNm;
    private String doctorDetail;
    private Major major;
    private String imgUrl;

    @QueryProjection //쿼리dsl로 조회한 결과를 MaindoctorDto 객체로 바로 받아올수 있다.
    public MainDoctorDto(Long id, String doctorNm, String doctorDetail, String imgUrl,Major major) {
        this.id = id;
        this.doctorNm = doctorNm;
        this.doctorDetail = doctorDetail;
        this.major =major;
        this.imgUrl = imgUrl;
    }
}
