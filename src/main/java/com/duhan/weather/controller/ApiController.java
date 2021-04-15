package com.duhan.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ApiController {
    String serviceKey = "nKJLr9q512o8UEWsDGYEgLdMFLNMgUId3e3mqbsMwGLK7Mf9ntFf38YmKFa5Dt3HIY%2B5NBbIoDYh3kBuHqMLiA%3D%3D";
    String baseDate = "20210415";
    String baseTime = "0800";
    String dataType = "JSON";
    String numOfRows = "250";
    // 인천시 청학동의 좌표
    String nx = "55";
    String ny = "24";
    String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?"
            + "serviceKey=" + serviceKey
            + "&pageNo=1"
            + "&numOfRows=" + numOfRows
            + "&dataType=" + dataType
            + "&base_date=" + baseDate
            + "&base_time=" + baseTime
            + "&nx=" + nx
            + "&ny=" + ny;


    @GetMapping("/")
    public String getData(HttpSession session, Model model) {
        System.out.println(session);
        return "hello world2";
    }


}
