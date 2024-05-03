package com.hospital.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDoctorImg is a Querydsl query type for DoctorImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDoctorImg extends EntityPathBase<DoctorImg> {

    private static final long serialVersionUID = -120086336L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDoctorImg doctorImg = new QDoctorImg("doctorImg");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final QDoctor doctor;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgName = createString("imgName");

    public final StringPath imgUrl = createString("imgUrl");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath oriImgName = createString("oriImgName");

    public final StringPath repImgYn = createString("repImgYn");

    public QDoctorImg(String variable) {
        this(DoctorImg.class, forVariable(variable), INITS);
    }

    public QDoctorImg(Path<? extends DoctorImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDoctorImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDoctorImg(PathMetadata metadata, PathInits inits) {
        this(DoctorImg.class, metadata, inits);
    }

    public QDoctorImg(Class<? extends DoctorImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
    }

}

