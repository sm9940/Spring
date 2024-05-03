package com.hospital.entity;

import com.hospital.constant.Day;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "available_day")
@Getter
@Setter
@ToString
public class AvailableDay {
    @Id
    @Column(name = "available_day_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Enumerated(EnumType.STRING)
    private Day day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "availableDay", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AvailableTime> availableTimes = new ArrayList<>();

    public void addAvailableTime(AvailableTime availableTime) {
        availableTimes.add(availableTime);
        availableTime.setAvailableDay(this);
    }
}
