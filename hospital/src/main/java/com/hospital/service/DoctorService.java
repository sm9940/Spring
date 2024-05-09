package com.hospital.service;

import com.hospital.dto.*;
import com.hospital.entity.AvailableDay;
import com.hospital.entity.AvailableTime;
import com.hospital.entity.Doctor;
import com.hospital.entity.DoctorImg;
import com.hospital.repository.DoctorImgRepository;
import com.hospital.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Long saveDoctor(DoctorFormDto doctorFormDto, List<MultipartFile> doctorImgFileList) throws Exception {
        Doctor doctor = doctorFormDto.createDoctor();
        doctorRepository.save(doctor);



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
    
    @Transactional(readOnly = true) //트랜잭션 읽기 전용(변경감지 수행 X) -> 성능향상
    public DoctorFormDto getDoctorDtl(Long DoctorId){
        //1. Doctor_img 테이블의 이미지를 가져온다.
        List<DoctorImg> DoctorImgList = doctorImgRepository.findByDoctorIdOrderByIdAsc(DoctorId);

        //DoctorImg Entity -> Dto 변환
        List<DoctorImgDto> DoctorImgDtoList = new ArrayList<>();
        for (DoctorImg DoctorImg: DoctorImgList) {
            DoctorImgDto doctorImgDto = DoctorImgDto.of(DoctorImg); //dto객체로 바뀜
            DoctorImgDtoList.add(doctorImgDto);//dto객체를 리스트에 넣어준다.
        }
        //2. Doctor 테이블에 있는 데이터를 가져온다.
        Doctor Doctor = doctorRepository.findById(DoctorId)
                .orElseThrow(EntityNotFoundException::new);

        //Doctor Entity -> Dto 변환
        DoctorFormDto doctorFormDto = DoctorFormDto.of(Doctor);
        //3.DoctorFormDto에 DoctorImgdtoList를 넣어준다. -> 화면단에서는 DoctorFormdto에서 이미지 리스트를 가지고옴
        doctorFormDto.setDoctorImgDtoList(DoctorImgDtoList);
        return doctorFormDto;
    }

    public Long updateDoctor(DoctorFormDto doctorFormDto, List<MultipartFile> doctorImgFileList) throws Exception {
        //1. item 엔티티 수정
        //update를 진행하기 전 무조건 select를 해온다.
        // -> 영속성 컨텍스트에 item 엔티티가 없다면 가져오기 위해
        Doctor doctor = doctorRepository.findById(doctorFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        //update 실행
        //한번 select를 진행하면 엔티티가 영속성 컨텍스트에저장되고
        //변경 감지 기능으로 인해 트랜잭션 커밋 시점에 엔티티와 DB에 저장된값이
        //다른 내용은 JPA에서 update해준다
        doctor.updateDoctor(doctorFormDto);

        //2. item_img 엔티티 수정

        List<Long> doctorImgIds = doctorFormDto.getDoctorImgids(); //상품 이미지 아이디 리스트 조회

        //5개의 이미지 파일을 업로드 했으므로 아래처럼 for문을 이용해 하나씩 이미지 업데이트를 진해
        for (int i = 0; i < doctorImgFileList.size(); i++) {
            //itemImgSerivce.updateItemImg(itemImg id, 이미지 파일);
            doctorImgService.updateDoctorImg(doctorImgIds.get(i), doctorImgFileList.get(i));
        }
        return doctor.getId(); //변경한 item의 id리턴
    }
    @Transactional(readOnly = true)
    public Page<Doctor> getAdminDoctorPage(DoctorSearchDto doctorSearchDto, Pageable pageable){
        Page<Doctor> doctorPage =doctorRepository.getAdminDoctorPage(doctorSearchDto,pageable);
        return doctorPage;
    }
    @Transactional(readOnly = true)
    public  Page<MainDoctorDto> getMainDoctorPage(DoctorSearchDto doctorSearchDto,Pageable pageable){
        Page<MainDoctorDto> mainDoctorPage = doctorRepository.getMainDoctorPage(doctorSearchDto,pageable);
        return mainDoctorPage;
    }

    public void deleteDoctor(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(EntityNotFoundException::new);

        // Cascade 설정을 통해 doctor의 자식 엔티티에 해당하는 doctorItem도 같이 삭제
        doctorRepository.delete(doctor); // delete
    }
}
