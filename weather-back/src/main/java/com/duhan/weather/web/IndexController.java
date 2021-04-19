package com.duhan.weather.web;

import com.duhan.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final WeatherService weatherService;

    @CrossOrigin
    @GetMapping("/")
    public String index() {
        weatherService.initialize();
        return "index";
    }
}
