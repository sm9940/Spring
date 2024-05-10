package com.hospital.entity;

import com.hospital.constant.RStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
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
    @Enumerated(EnumType.STRING)
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

        reservation.setRStatus(RStatus.RESERVATION);
        return reservation;
    }
}
