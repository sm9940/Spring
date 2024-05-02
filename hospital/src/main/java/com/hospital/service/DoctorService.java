package com.hospital.service;

import com.hospital.dto.DoctorFormDto;
import com.hospital.entity.Doctor;
import com.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorImgService doctorImgService;

    public Long saveDoctor(DoctorFormDto doctorFormDto, List<MultipartFile> doctorImgFileList){
        Doctor doctor = doctorFormDto.createDoctor();
        doctor
    }
}
