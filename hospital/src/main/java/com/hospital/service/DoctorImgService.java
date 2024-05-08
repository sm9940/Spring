package com.hospital.service;

import com.hospital.entity.DoctorImg;
import com.hospital.repository.DoctorImgRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public void saveDoctorImg(DoctorImg doctorImg, MultipartFile doctorImgFile) throws Exception {
        String oriImgName = doctorImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if (!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(doctorImgLocation,
                    oriImgName, doctorImgFile.getBytes());

            imgUrl = "/images/doctor/" + imgName;
        }

        doctorImg.updateDoctorImg(oriImgName, imgName, imgUrl);
        doctorImgRepository.save(doctorImg);
    }

    public void updateDoctorImg(Long doctorImgId, MultipartFile doctorImgFile) throws Exception {
        if (!doctorImgFile.isEmpty()) { //첨부한 이미지 파일이 있으면
            //1. 서버에 있는 이미지를 가지고 와서 수정해준다.
            DoctorImg saveDoctorImg = doctorImgRepository.findById(doctorImgId).orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일을 c:/shop/Doctor 폴더에서 삭제
            if (!StringUtils.isEmpty(saveDoctorImg.getImgName())) {
                fileService.deleteFile(doctorImgLocation + "/" + saveDoctorImg.getImgName());
            }

            //수정된 이미지 파일을 경로에 업로드
            String oriImgName = doctorImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(doctorImgLocation, oriImgName, doctorImgFile.getBytes());
            String imgUrl = "/images/doctor/" + imgName;

            //2. Doctor_img 테이블에 저장된 데이터를 수정해준다.
            //update (JPA가 자동감지)
            saveDoctorImg.updateDoctorImg(oriImgName, imgName, imgUrl);

        }
    }
}
