package com.hospital.entity;

import com.hospital.constant.Major;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@ToString
public class Doctor {
    @Id
    @Column(name = "doctor_id")
    private Long id;
    @Column(nullable = false)
    private String doctorNm;
    @Enumerated(EnumType.STRING)
    private Major major;
    @Lob
    @Column(nullable = false, columnDefinition = "longtext")
    private String doctorDetail;
}
