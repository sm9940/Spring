package com.hospital.service;

import com.hospital.dto.AvailableDayDto;
import com.hospital.dto.AvailableTimeDto;
import com.hospital.entity.AvailableDay;
import com.hospital.entity.AvailableTime;
import com.hospital.repository.AvailableDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AvailableDayService {

    private final AvailableDayRepository availableDayRepository;
    private final AvailableTimeService availableTimeService;

    public void saveAvailableDays(List<AvailableDayDto> availableDayDtoList) {
        for (AvailableDayDto availableDayDto : availableDayDtoList) {
            AvailableDay availableDay = availableDayDto.createAvailableDay();
            availableDayRepository.save(availableDay);

            // AvailableTime 저장
            for (AvailableTimeDto availableTimeDto : availableDayDto.getAvailableTimeDtoList()) {
                availableTimeService.saveAvailableTime(availableTimeDto, availableDay);
            }
        }
    }


}