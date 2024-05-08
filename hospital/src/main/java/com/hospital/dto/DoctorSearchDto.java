package com.hospital.dto;

import com.hospital.constant.Major;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorSearchDto {
    private String searchDateType;
    private Major searchMajor;
    private String searchBy;
    private String searchQuery = "";
}
