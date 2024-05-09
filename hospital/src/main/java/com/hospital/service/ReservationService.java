package com.hospital.service;

import com.hospital.constant.RStatus;
import com.hospital.dto.DoctorFormDto;
import com.hospital.dto.ReservationDto;
import com.hospital.entity.Doctor;
import com.hospital.entity.DoctorImg;
import com.hospital.entity.Member;
import com.hospital.entity.Reservation;
import com.hospital.repository.DoctorImgRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.MemberRepository;
import com.hospital.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {
    private final DoctorRepository doctorRepository;
    private final MemberRepository memberRepository;
    private final DoctorImgRepository doctorImgRepository;
    private ReservationRepository reservationRepository;
    public Reservation createReservation(ReservationDto reservationDto, Principal principal) {
        Member member = memberRepository.findById(principal.)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Doctor doctor = doctorRepository.findById(reservationDto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setDoctor(doctor);
        reservation.setRDate(reservationDto.getRDate());
        reservation.setRTime(reservationDto.getRTime());
        reservation.setRStatus(RStatus.RESERVATION);
        return reservation;
    }
}
