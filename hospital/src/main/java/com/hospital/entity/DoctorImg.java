package com.hospital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctor_img")
@Getter
@Setter
@ToString
public class DoctorImg extends BaseEntity{
    @Id
    @Column(name="doctor_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public void updateDoctorImg(String oriImgName, String imgName, String imgUrl) {
    this.oriImgName=oriImgName;
    this.imgName = imgName;
    this.imgUrl = imgUrl;
    }
}
