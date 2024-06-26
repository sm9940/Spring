package com.hospital.entity;

import com.hospital.constant.Major;
import com.hospital.dto.DoctorFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@ToString
public class Doctor extends BaseEntity{
    @Id
    @Column(name = "doctor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String doctorNm;
    @Enumerated(EnumType.STRING)
    private Major major;
    @Lob
    @Column(nullable = false, columnDefinition = "longtext")
    private String doctorDetail;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<AvailableDay> availableDays =new ArrayList<>();

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoctorImg> doctorImgs = new ArrayList<>();
    public  void updateDoctor(DoctorFormDto DoctorFormDto){
        this.doctorNm = DoctorFormDto.getDoctorNm();
        this.doctorDetail = DoctorFormDto.getDoctorDetail();
        this.major= DoctorFormDto.getMajor();
    }




}
