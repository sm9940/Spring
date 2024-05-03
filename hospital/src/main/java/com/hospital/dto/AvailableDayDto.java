package com.hospital.dto;

import com.hospital.constant.Day;
import com.hospital.entity.AvailableDay;
import com.hospital.entity.AvailableTime;
import com.hospital.entity.Doctor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AvailableDayDto {
    private Long Id;
    private Day day;

    private List<AvailableTimeDto> availableTimeDtoList =new ArrayList<>();
    private static ModelMapper modelMapper = new ModelMapper();

    public AvailableDay createAvailableDay() {
        return modelMapper.map(this, AvailableDay.class);
    }

    public static AvailableDayDto of(AvailableDay availableDay) {
        return modelMapper.map(availableDay, AvailableDayDto.class);
    }
}
