package com.duhan.weather.web.dto;

import com.duhan.weather.domain.Weather;
import lombok.Getter;

@Getter
public class WeatherListResponseDto {
    private Long id;
//    private String baseDate;
//    private String baseTime;
    private String category;
    private String fcstDate;
    private String fcstTime;
    private String fcstValue;
//    private Long nx;
//    private Long ny;

    public WeatherListResponseDto(Weather entity) {
        this.category = entity.getCategory();
        this.fcstDate = entity.getFcstDate();
        this.fcstTime = entity.getFcstTime();
        this.fcstValue = entity.getFcstValue();
    }
}
