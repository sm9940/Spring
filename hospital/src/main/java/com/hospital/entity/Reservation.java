package com.hospital.entity;

import com.hospital.constant.RStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@ToString
public class Reservation {
    @Id
    @Column(name = "r_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rDate;
    private String rTime;
    private RStatus rStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    public static Reservation createReservation(Member member, Doctor doctor, String rDate, String rTime) {
        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setDoctor(doctor);
        reservation.setRDate(rDate);
        reservation.setRTime(rTime);
        reservation.setRStatus(RStatus.RESERVATION);
        return reservation;
    }
}
