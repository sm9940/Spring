package com.hospital.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDoctor is a Querydsl query type for Doctor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDoctor extends EntityPathBase<Doctor> {

    private static final long serialVersionUID = 1205689347L;

    public static final QDoctor doctor = new QDoctor("doctor");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<AvailableDay, QAvailableDay> availableDays = this.<AvailableDay, QAvailableDay>createList("availableDays", AvailableDay.class, QAvailableDay.class, PathInits.DIRECT2);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath doctorDetail = createString("doctorDetail");

    public final ListPath<DoctorImg, QDoctorImg> doctorImgs = this.<DoctorImg, QDoctorImg>createList("doctorImgs", DoctorImg.class, QDoctorImg.class, PathInits.DIRECT2);

    public final StringPath doctorNm = createString("doctorNm");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.hospital.constant.Major> major = createEnum("major", com.hospital.constant.Major.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DatePath<java.time.LocalDate> regDate = _super.regDate;

    //inherited
    public final DatePath<java.time.LocalDate> updateDate = _super.updateDate;

    public QDoctor(String variable) {
        super(Doctor.class, forVariable(variable));
    }

    public QDoctor(Path<? extends Doctor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDoctor(PathMetadata metadata) {
        super(Doctor.class, metadata);
    }

}

