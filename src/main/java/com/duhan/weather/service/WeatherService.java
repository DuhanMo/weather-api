package com.duhan.weather.service;

import com.duhan.weather.domain.Weather;
import com.duhan.weather.domain.WeatherRepository;
import com.duhan.weather.web.dto.WeatherSaveRequestDto;
import lombok.RequiredArgsConstructor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;

    public String findDate(){
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
        Date now = new Date();

        String time = timeFormat.format(now);
        String date;

        if (Integer.parseInt(time) > 0 && Integer.parseInt(time) < 5) {
            Date before = new Date(now.getTime() + (1000 * 60 * 60 * 24 * -1));
            date = dayFormat.format(before);
        } else {
            date = dayFormat.format(now);
        }
        return date;
    }

    public String initialize(){
        String dayAfterTomorrow = Integer.toString(Integer.parseInt(findDate())+2);
        List<Weather> result = weatherRepository.findByFcstDate(dayAfterTomorrow);
//        System.out.println(result);
        if (!result.isEmpty()) { //
            System.out.println("현재일 기준 업데이트가 된 상태입니다.");
        } else { // 내일모레 데이터가 없을 때 (업데이트가 안되었다는 뜻) -> 이전 데이터 삭제하고 새롭게 api서버에서 데이터를 받아와야함
            System.out.println("현재일 기준 업데이트가 되지 않았습니다. 새로운 데이터를 받아옵니다.");
            deleteBeforeData();
            saveInitData();
        }
        return "find 내일모레!";
    }
    public String deleteBeforeData() {
        weatherRepository.deleteAll();
        return "ok";
    }

    public Long saveInitData() {
        String serviceKey = "nKJLr9q512o8UEWsDGYEgLdMFLNMgUId3e3mqbsMwGLK7Mf9ntFf38YmKFa5Dt3HIY%2B5NBbIoDYh3kBuHqMLiA%3D%3D";
        String baseDate = findDate();
        String baseTime = "0500";
        String dataType = "JSON";
        String numOfRows = "180"; // 다음 날 오후 21시 까지의 정보 요청
        // 서울, 부산, 대구, 인천, 광주, 대전, 울산, 세종, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주 의 x,y 좌표
        String[][] location = {{"60", "27"}, {"98", "76"}, {"89", "90"}, {"55", "124"}, {"58", "74"}
                , {"67", "100"}, {"102", "84"}, {"66", "103"}, {"60", "120"}, {"66", "103"}
                , {"73", "134"}, {"69", "107"}, {"68", "100"}, {"63", "89"}, {"51", "67"}, {"89", "91"}
                , {"91", "77"}, {"52", "38"}};

        String nx;
        String ny;
        for (int i = 0; i < location.length; i++) {
            nx = location[i][0];
            ny = location[i][1];
            String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?"
                    + "serviceKey=" + serviceKey
                    + "&pageNo=1"
                    + "&numOfRows=" + numOfRows
                    + "&dataType=" + dataType
                    + "&base_date=" + baseDate
                    + "&base_time=" + baseTime
                    + "&nx=" + nx
                    + "&ny=" + ny;
            System.out.println(url);
            WeatherGetJsonArray weatherGetJsonArray = new WeatherGetJsonArray();
            JSONArray jsonArray = weatherGetJsonArray.getJsonArray(url);
            WeatherSaveRequestDto requestDto = new WeatherSaveRequestDto();
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject weatherObject = (JSONObject) jsonArray.get(j);
                requestDto.setBaseDate((String) weatherObject.get("baseDate"));
                requestDto.setBaseTime((String) weatherObject.get("baseTime"));
                requestDto.setCategory((String) weatherObject.get("category"));
                requestDto.setFcstDate((String) weatherObject.get("fcstDate"));
                requestDto.setFcstTime((String) weatherObject.get("fcstTime"));
                requestDto.setFcstValue((String) weatherObject.get("fcstValue"));
                requestDto.setNx((Long) weatherObject.get("nx"));
                requestDto.setNy((Long) weatherObject.get("ny"));
                weatherRepository.save(requestDto.toEntity());
            }
        }
        return null;
    }
}
