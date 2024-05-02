package com.hospital.controller;

import com.hospital.dto.DoctorFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DoctorController {
    @GetMapping(value = "/admin/doctor/new")
    public String DoctorForm(Model model){
        model.addAttribute("doctorFormDto",new DoctorFormDto());
        return "doctor/doctorForm";
    }
}
