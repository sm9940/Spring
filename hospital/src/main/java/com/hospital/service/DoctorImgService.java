package com.hospital.service;

import com.hospital.entity.DoctorImg;
import com.hospital.repository.DoctorImgRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@Transactional //하나의 메소드가 트랜잭션으로 묶인다(DB Exception 혹은 다른 Exception 발생시 롤백)
@RequiredArgsConstructor
public class DoctorImgService {
    @Value("${doctorImgLocation}")
    private String doctorImgLocation;
    private final DoctorImgRepository doctorImgRepository;
    private final FileService fileService;

    public void saveDoctorImg(DoctorImg doctorImg, MultipartFile doctorImgFile) throws Exception{
        String oriImgName = doctorImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(doctorImgLocation,
                    oriImgName,doctorImgFile.getBytes());

            imgUrl = "/images/img/"+imgName;
        }

        doctorImg.updateDoctorImg(oriImgName,imgName,imgUrl);
        doctorImgRepository.save(doctorImg);
    }
}
