package com.hospital.controller;

import com.hospital.dto.DoctorFormDto;
import com.hospital.entity.AvailableDay;
import com.hospital.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorMessage","의료진 등록 도중 에러가 발생했습니다.");
            return "doctor/doctorForm";
        }
        return "redirect:/";
        }
}
