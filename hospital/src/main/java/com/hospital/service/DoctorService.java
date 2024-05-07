package com.hospital.service;

import com.hospital.dto.AvailableDayDto;
import com.hospital.dto.AvailableTimeDto;
import com.hospital.dto.DoctorFormDto;
import com.hospital.dto.DoctorImgDto;
import com.hospital.entity.AvailableDay;
import com.hospital.entity.AvailableTime;
import com.hospital.entity.Doctor;
import com.hospital.entity.DoctorImg;
import com.hospital.repository.DoctorImgRepository;
import com.hospital.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorImgService doctorImgService;
    private final DoctorImgRepository doctorImgRepository;
    private final AvailableDayService availableDayService; // AvailableDayService 주입
    private final AvailableTimeService availableTimeService; // AvailableTimeService 주입

    public Long saveDoctor(DoctorFormDto doctorFormDto, List<MultipartFile> doctorImgFileList) throws Exception {
        Doctor doctor = doctorFormDto.createDoctor();
        doctorRepository.save(doctor);

        // AvailableDay 및 AvailableTime 저장
        availableDayService.saveAvailableDays(doctorFormDto.getAvailableDayDtoList());

        // DoctorImg 저장
        for(int i = 0 ; i<doctorImgFileList.size(); i++){
            DoctorImg doctorImg = new DoctorImg();
            doctorImg.setDoctor(doctor);

            if(i==0){
                doctorImg.setRepImgYn("Y");
            } else {
                doctorImg.setRepImgYn("N");
            }
            doctorImgService.saveDoctorImg(doctorImg,doctorImgFileList.get(i));
        }

        return doctor.getId();
    }

    public DoctorFormDto getDoctorDtl(Long doctorId) {
        List<DoctorImg> doctorImgList = doctorImgRepository.findByDoctorIdOrderByIdAsc(doctorId);

        List<DoctorImgDto> doctorImgDtoList = new ArrayList<>();

        for (DoctorImg doctorImg : doctorImgList) {
            DoctorImgDto doctorImgDto = DoctorImgDto.of(doctorImg);
            doctorImgDtoList.add(doctorImgDto);
        }
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(EntityNotFoundException::new);

        DoctorFormDto doctorFormDto = DoctorFormDto.of(doctor);

        doctorFormDto.setDoctorImgDtoList(doctorImgDtoList);
        return doctorFormDto;
    }
}
