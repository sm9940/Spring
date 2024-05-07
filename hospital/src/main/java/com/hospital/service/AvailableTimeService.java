package com.hospital.service;

import com.hospital.dto.AvailableTimeDto;
import com.hospital.entity.AvailableDay;
import com.hospital.entity.AvailableTime;
import com.hospital.repository.AvailableTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AvailableTimeService {

    private final AvailableTimeRepository availableTimeRepository;

    public void saveAvailableTime(AvailableTimeDto availableTimeDto, AvailableDay availableDay) {
        AvailableTime availableTime = availableTimeDto.createAvailableTime();
        availableTime.setAvailableDay(availableDay); // 해당 AvailableTime에 AvailableDay 설정
        availableTimeRepository.save(availableTime);
    }

    // 다른 메소드들 추가 가능...
}