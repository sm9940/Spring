package com.hospital.repository;

import com.hospital.constant.Major;
import com.hospital.dto.DoctorSearchDto;
import com.hospital.dto.MainDoctorDto;
import com.hospital.dto.QMainDoctorDto;
import com.hospital.entity.Doctor;

import com.hospital.entity.QDoctor;
import com.hospital.entity.QDoctorImg;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

public class DoctorRepositoryCustomImpl implements DoctorRepositoryCustom{
    private JPAQueryFactory queryFactory;

    public DoctorRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDate dateTime = LocalDate.now(); //현재시간

        if(StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1); //1일전
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1); //1주일 전
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1); //1개월 전
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6); //6개월 전
        }
        return QDoctor.doctor.regDate.after(dateTime);
    }
    private BooleanExpression searchMajorEq(Major searchMajor) {
        return searchMajor == null ? null : QDoctor.doctor.major.eq(searchMajor);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if(StringUtils.equals("DoctorNm", searchBy)) {
            return QDoctor.doctor.doctorNm.like("%" + searchQuery + "%");
        }

        return null;
    }
    @Override
    public Page<Doctor> getAdminDoctorPage(DoctorSearchDto doctorSearchDto, Pageable pageable) {
        List<Doctor> content = queryFactory.selectFrom(QDoctor.doctor)
                .where(regDtsAfter(doctorSearchDto.getSearchDateType()),
                        searchMajorEq(doctorSearchDto.getSearchMajor()),
                        searchByLike(doctorSearchDto.getSearchBy(), doctorSearchDto.getSearchQuery()))
                .orderBy(QDoctor.doctor.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(Wildcard.count).from(QDoctor.doctor)
                .where(regDtsAfter(doctorSearchDto.getSearchDateType()),
                        searchMajorEq(doctorSearchDto.getSearchMajor()),
                        searchByLike(doctorSearchDto.getSearchBy(), doctorSearchDto.getSearchQuery()))
                .fetchOne();
        return new PageImpl<>(content, pageable, total);
    }
    private  BooleanExpression doctorNmLike(String searchQuery){
        return StringUtils.isEmpty(searchQuery)  ? null : QDoctor.doctor.doctorNm.like("%"+searchQuery+"%");
    }

    @Override
    public Page<MainDoctorDto> getMainDoctorPage(DoctorSearchDto doctorSearchDto, Pageable pageable) {
        
        QDoctor doctor = QDoctor.doctor;
        QDoctorImg doctorImg = QDoctorImg.doctorImg;
        
        List<MainDoctorDto> content = queryFactory.select
                        (new QMainDoctorDto(doctor.id,doctor.doctorNm,doctor.doctorDetail,doctorImg.imgUrl,doctor.major))
                .from(doctorImg)
                .join(doctorImg.doctor,doctor)
                .where(doctorImg.repImgYn.eq("Y"))
                .where(doctorNmLike(doctorSearchDto.getSearchQuery()))
                .orderBy(doctor.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.select(Wildcard.count)
                .from(doctorImg)
                .join(doctorImg.doctor,doctor)
                .where(doctorImg.repImgYn.eq("Y"))
                .where(doctorNmLike(doctorSearchDto.getSearchQuery()))
                .fetchOne();
        return new PageImpl<>(content,pageable,total);
    }
}
