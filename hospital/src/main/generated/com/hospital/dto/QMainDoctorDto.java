package com.hospital.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hospital.dto.QMainDoctorDto is a Querydsl Projection type for MainDoctorDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainDoctorDto extends ConstructorExpression<MainDoctorDto> {

    private static final long serialVersionUID = 500238961L;

    public QMainDoctorDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> doctorNm, com.querydsl.core.types.Expression<String> doctorDetail, com.querydsl.core.types.Expression<String> imgUrl, com.querydsl.core.types.Expression<com.hospital.constant.Major> major) {
        super(MainDoctorDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, com.hospital.constant.Major.class}, id, doctorNm, doctorDetail, imgUrl, major);
    }

}

