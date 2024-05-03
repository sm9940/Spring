package com.hospital.repository;

import com.hospital.entity.AvailableDay;
import com.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableDayRepository extends JpaRepository<AvailableDay,Long> {
}
