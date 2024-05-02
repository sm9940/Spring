package com.hospital.repository;

import com.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DoctorRepository extends JpaRepository<Doctor,Long>, QuerydslPredicateExecutor<Doctor>,DoctorRepositoryCustom {
}
