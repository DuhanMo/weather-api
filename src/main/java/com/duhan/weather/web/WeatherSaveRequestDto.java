package com.duhan.weather.web;

import com.duhan.weather.domain.Weather;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherSaveRequestDto {
    private String baseDate;
    private String baseTime;
    private String category;
    private String fcstDate;
    private String fcstTime;
    private String fcstValue;
    private Long nx;
    private Long ny;

    public Weather toEntity(){
        return Weather.builder()
                .baseDate(baseDate)
                .baseTime(baseTime)
                .category(category)
                .fcstDate(fcstDate)
                .fcstTime(fcstTime)
                .fcstValue(fcstValue)
                .nx(nx)
                .ny(ny)
                .build();
    }
}
