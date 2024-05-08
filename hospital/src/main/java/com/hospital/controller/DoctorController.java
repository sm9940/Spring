package com.hospital.controller;

import com.hospital.dto.DoctorFormDto;
import com.hospital.dto.DoctorSearchDto;
import com.hospital.dto.MainDoctorDto;
import com.hospital.entity.AvailableDay;
import com.hospital.entity.Doctor;
import com.hospital.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping(value = "/admin/doctor/new")
    public String DoctorForm(Model model){
        model.addAttribute("doctorFormDto",new DoctorFormDto());
        return "doctor/doctorForm";
    }
    @PostMapping(value = "/admin/doctor/new")
    public String doctorNew(@Valid DoctorFormDto doctorFormDto, BindingResult bindingResult
    , Model model, @RequestParam("doctorImgFile")List<MultipartFile> doctorImgFileList){
        if(bindingResult.hasErrors()) return "doctor/doctorForm";

        if(doctorImgFileList.get(0).isEmpty()){
            model.addAttribute("errorMessage","대표 이미지를 넣어주세요");
            return "doctor/doctorForm";
        }

        try {
            doctorService.saveDoctor(doctorFormDto,doctorImgFileList);
            return "redirect:/";
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorMessage","의료진 등록 도중 에러가 발생했습니다.");
            return "doctor/doctorForm";
        }

        }
    @GetMapping(value = {"/admin/doctors","/admin/doctors/{page}"} )
    public String DoctorManage(DoctorSearchDto doctorSearchDto,
                               @PathVariable("page") Optional<Integer> page, Model model){
        //PageRequest.of(페이지 번호, 한 페이지당 조회할 데이터 갯수);
        //URL path에 페이지가 있으면 해당 페이지 번호를 조회하고 없다면 0페이지(첫번째 페이지)를 조회
        Pageable pageable= PageRequest.of(page.isPresent()? page.get(): 0,3);

        Page<Doctor> Doctors = doctorService.getAdminDoctorPage(doctorSearchDto,pageable);

        model.addAttribute("doctors", Doctors);
        model.addAttribute("doctorSearchDto",doctorSearchDto);
        //상품관리 페이지 하단에 보여줄 최대 페이지 번호
        model.addAttribute("maxPage",5);
        return "doctor/doctorList";
    }
    @GetMapping(value = "/doctors")
    public String Doctors(Model model,DoctorSearchDto doctorSearchDto,
                          @RequestParam(value = "page")Optional<Integer> page){

        Pageable pageable=PageRequest.of(page.isPresent()? page.get():0,8);
        Page<MainDoctorDto> doctors = doctorService.getMainDoctorPage(doctorSearchDto,pageable);
        model.addAttribute("doctors",doctors);
        model.addAttribute("doctorSearchDto",doctorSearchDto);
        model.addAttribute("maxPage",5);
        return "doctor/doctors";
    }
    @GetMapping(value = "/admin/doctor/{doctorId}")
    public String DoctorDtl(@PathVariable("doctorId") Long doctorId, Model model) {
        try {
            DoctorFormDto doctorFormDto = doctorService.getDoctorDtl(doctorId);
            model.addAttribute("doctorFormDto", doctorFormDto);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "상품정보를 가져오는 도중 에러가 발생했습니다.");

            //에러발생시 비어있는 객체를 넘겨준다.
            model.addAttribute("doctorFormDto", new DoctorFormDto());
            return "doctor/doctorModifyForm"; //등록화면으로 다시 이동
        }

        return "Doctor/DoctorModifyForm";
    }
    @PostMapping(value = "/admin/doctor/{doctorId}")
    public String DoctorUpdate(@Valid DoctorFormDto doctorFormDto, Model model, BindingResult bindingResult,
                             @RequestParam("doctorImgFile") List<MultipartFile> doctorImgFileList,
                             @PathVariable("doctorId") Long doctorId) {

        if(bindingResult.hasErrors()) return "doctor/doctorForm"; //유효성 체크에서 걸리면

        DoctorFormDto getDoctorFormDto = doctorService.getDoctorDtl(doctorId);

        //상품등록 전에 첫번째 이미지가 있는지 없는지 검사(첫번째 이미지는 필수 입력값)
        //DoctorFormDto.getId() ==null => 이미지 외에 다른 내용만 수정햇을때 if문에 걸리는 경우를 방지
        if(doctorImgFileList.get(0).isEmpty() && doctorFormDto.getId() == null) {
            model.addAttribute("errorMessage",
                    "첫번째 의료진 이미지는 필수 입력입니다.");
            model.addAttribute("doctorFormDto", getDoctorFormDto);
            return "doctor/doctorModifyForm";
        }

        try {
            doctorService.updateDoctor(doctorFormDto, doctorImgFileList);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "의료진 정보 수정중 에러가 발생했습니다.");
            model.addAttribute("doctorFormDto", getDoctorFormDto);
            return "doctor/doctorModifyForm";
        }

        return "redirect:/";

    }
    
    @GetMapping(value = "/doctors/{doctorId}")
    public String doctorDtl(Model model, @PathVariable("doctorId") Long doctorId){
        DoctorFormDto doctorFormDto=doctorService.getDoctorDtl(doctorId);
        model.addAttribute("doctor",doctorFormDto);
        return "doctor/doctorDtl";
    }
}
