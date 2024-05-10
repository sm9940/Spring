package com.hospital.service;

import com.hospital.config.MemberContext;
import com.hospital.constant.RStatus;
import com.hospital.dto.ReservationDto;
import com.hospital.entity.Doctor;
import com.hospital.entity.Member;
import com.hospital.entity.Reservation;
import com.hospital.repository.DoctorImgRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.MemberRepository;
import com.hospital.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {
    private final DoctorRepository doctorRepository;
    private final MemberRepository memberRepository;
    private final DoctorImgRepository doctorImgRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public Long createReservation(ReservationDto reservationDto) {
        // 현재 사용자의 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 인증 정보에서 MemberContext 가져오기
        MemberContext memberContext = (MemberContext) authentication.getPrincipal();
        // MemberContext에서 memberId 가져오기
        Long memberId = memberContext.getId();
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        Doctor doctor = doctorRepository.findById(reservationDto.getDoctorId())
                .orElseThrow(EntityNotFoundException::new);

        // 예약 객체 생성
        // 예약 객체 생성
        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setDoctor(doctor);
        reservation.setRDate(reservationDto.getRDate());
        reservation.setRTime(reservationDto.getRTime());
        reservation.setRStatus(RStatus.RESERVATION);

        // 데이터베이스에 예약 정보 저장
        reservationRepository.save(reservation);

        return reservation.getId();
    }
    }

