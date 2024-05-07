package com.hospital.repository;

import com.hospital.entity.AvailableDay;
import com.hospital.entity.AvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableTimeRepository extends JpaRepository<AvailableTime,Long> {
}
