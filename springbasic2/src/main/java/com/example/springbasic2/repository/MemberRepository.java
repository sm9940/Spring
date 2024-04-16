package com.example.springbasic2.repository;

import com.example.springbasic2.entity.Member;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;

public interface MemberRepository extends JpaAttributeConverter<Member,Long> {
}
