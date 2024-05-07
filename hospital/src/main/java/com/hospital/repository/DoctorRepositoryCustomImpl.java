package com.hospital.repository;

import com.hospital.entity.Doctor;

import com.hospital.entity.QDoctor;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DoctorRepositoryCustomImpl implements DoctorRepositoryCustom{

    @Override
    public Page<Doctor> getAdminDoctorPage() {
        return null;
    }
}
