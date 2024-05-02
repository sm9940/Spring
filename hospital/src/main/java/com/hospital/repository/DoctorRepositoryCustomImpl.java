package com.hospital.repository;

import com.hospital.entity.Doctor;
import org.springframework.data.domain.Page;

public class DoctorRepositoryCustomImpl implements DoctorRepositoryCustom{
    @Override
    public Page<Doctor> getAdminDoctorPage() {
        return null;
    }
}
