package com.hospital.repository;

import com.hospital.entity.Doctor;
import org.springframework.data.domain.Page;

public interface DoctorRepositoryCustom {
    Page<Doctor> getAdminDoctorPage();
}
