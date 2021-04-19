package com.duhan.weather.web;

import com.duhan.weather.domain.Weather;
import com.duhan.weather.domain.WeatherRepository;
import com.duhan.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final WeatherService weatherService;
    private final WeatherRepository weatherRepository;


    @GetMapping("/api/save")
    public Long saveInitData() {
        return weatherService.saveInitData();
    }
    @GetMapping("/api/all")
    public List<Weather> getAll() {
        return weatherRepository.findAll();
    }

    @GetMapping("/api/delete")
    public String deleteBeforeData() {
        return weatherService.deleteBeforeData();
    }


}
