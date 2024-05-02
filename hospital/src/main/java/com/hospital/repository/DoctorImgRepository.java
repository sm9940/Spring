package com.hospital.repository;

import com.hospital.entity.DoctorImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorImgRepository extends JpaRepository<DoctorImg,Long> {
    List<DoctorImg> findByDoctorIdOrderByIdAsc(Long doctorId);
    DoctorImg findByDoctorIdAndRepImgYn(Long doctorId,String RepImgYn);
}
