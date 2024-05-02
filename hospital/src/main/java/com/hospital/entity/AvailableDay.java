package com.hospital.entity;

import com.hospital.constant.Day;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "available_day")
@Getter
@Setter
@ToString
public class AvailableDay {
    @Id
    @Column(name = "available_day_id")
    private Long Id;
    @Enumerated(EnumType.STRING)
    private Day day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
