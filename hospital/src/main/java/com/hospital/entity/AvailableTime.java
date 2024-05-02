package com.hospital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;

@Entity
@Table(name = "available_time")
@Getter
@Setter
@ToString
public class AvailableTime {
    @Id
    @Column(name = "available_time_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Time availableTime;

    @ManyToOne
    @JoinColumn(name = "available_day_id")
    private AvailableDay availableDay;
}
