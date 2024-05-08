package com.hospital.repository;

import com.hospital.dto.DoctorSearchDto;
import com.hospital.dto.MainDoctorDto;
import com.hospital.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorRepositoryCustom {
    Page<Doctor> getAdminDoctorPage(DoctorSearchDto doctorSearchDto, Pageable pageable);
    Page<MainDoctorDto> getMainDoctorPage(DoctorSearchDto doctorSearchDto, Pageable pageable);
}
