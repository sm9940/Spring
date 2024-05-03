package com.hospital.dto;

import com.hospital.entity.AvailableTime;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.sql.Time;

@Getter
@Setter
public class AvailableTimeDto {
    private Long id;
    private Time startTime;
    private Time endTime;

    private static ModelMapper modelMapper = new ModelMapper();

    public AvailableTime createAvailableTime() {
        return modelMapper.map(this, AvailableTime.class);
    }

    public static AvailableTimeDto of(AvailableTime availableTime) {
        return modelMapper.map(availableTime, AvailableTimeDto.class);
    }
}
