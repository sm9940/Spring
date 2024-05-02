package com.hospital.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAvailableTime is a Querydsl query type for AvailableTime
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAvailableTime extends EntityPathBase<AvailableTime> {

    private static final long serialVersionUID = -703876654L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAvailableTime availableTime1 = new QAvailableTime("availableTime1");

    public final QAvailableDay availableDay;

    public final TimePath<java.sql.Time> availableTime = createTime("availableTime", java.sql.Time.class);

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public QAvailableTime(String variable) {
        this(AvailableTime.class, forVariable(variable), INITS);
    }

    public QAvailableTime(Path<? extends AvailableTime> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAvailableTime(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAvailableTime(PathMetadata metadata, PathInits inits) {
        this(AvailableTime.class, metadata, inits);
    }

    public QAvailableTime(Class<? extends AvailableTime> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.availableDay = inits.isInitialized("availableDay") ? new QAvailableDay(forProperty("availableDay"), inits.get("availableDay")) : null;
    }

}

