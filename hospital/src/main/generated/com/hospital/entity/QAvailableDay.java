package com.hospital.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAvailableDay is a Querydsl query type for AvailableDay
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAvailableDay extends EntityPathBase<AvailableDay> {

    private static final long serialVersionUID = 808562679L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAvailableDay availableDay = new QAvailableDay("availableDay");

    public final EnumPath<com.hospital.constant.Day> day = createEnum("day", com.hospital.constant.Day.class);

    public final QDoctor doctor;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public QAvailableDay(String variable) {
        this(AvailableDay.class, forVariable(variable), INITS);
    }

    public QAvailableDay(Path<? extends AvailableDay> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAvailableDay(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAvailableDay(PathMetadata metadata, PathInits inits) {
        this(AvailableDay.class, metadata, inits);
    }

    public QAvailableDay(Class<? extends AvailableDay> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
    }

}

