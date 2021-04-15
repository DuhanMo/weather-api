package com.duhan.weather.service;

import com.duhan.weather.domain.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;
}
