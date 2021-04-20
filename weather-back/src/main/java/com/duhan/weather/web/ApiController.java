package com.duhan.weather.web;

import com.duhan.weather.domain.Weather;
import com.duhan.weather.domain.WeatherRepository;
import com.duhan.weather.service.WeatherService;
import com.duhan.weather.web.dto.WeatherListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @CrossOrigin
    @GetMapping("/api/all")
    public List<Weather> getAll() {
        return weatherRepository.findAll();
    }

    @GetMapping("/api/delete")
    public String deleteBeforeData() {
        return weatherService.deleteBeforeData();
    }

    @CrossOrigin
    @GetMapping("/api/weather")
    public List<WeatherListResponseDto> findByXy(
            @RequestParam(value = "xy[]") List<Long> xy){
        System.out.println(xy);
        return weatherService.findByNxAndNy(xy);
    }
}
