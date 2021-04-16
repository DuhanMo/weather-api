package com.duhan.weather.service;

import com.duhan.weather.domain.Weather;
import com.duhan.weather.domain.WeatherRepository;
import com.duhan.weather.web.WeatherSaveRequestDto;
import lombok.RequiredArgsConstructor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;

    // 09시 10개
    // 12시 10개
    // ... 09, 12, 15, 18, 21, 00, 03, 06,  16일 ~ 17일
    // ... 09, 12, 15, 18, 21, 00, 03, 06   17일 ~ 18일
    // ... 09                               18일
    String serviceKey = "nKJLr9q512o8UEWsDGYEgLdMFLNMgUId3e3mqbsMwGLK7Mf9ntFf38YmKFa5Dt3HIY%2B5NBbIoDYh3kBuHqMLiA%3D%3D";
    String baseDate = "20210416";
    String baseTime = "0500";
    String dataType = "JSON";
    String numOfRows = "180"; // 이튿날 오전 09시 까지의 정보 요청
    // 서울, 부산, 대구, 인천, 광주, 대전, 울산, 세종, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주 의 x,y 좌표
    String[][] array = {{"60","27"},{"98","76"},{"89","90"},{"55","124"},{"58","74"}
            ,{"67","100"},{"102","84"},{"66","103"},{"60","120"},{"66","103"}
            ,{"73","134"},{"69","107"},{"68","100"},{"63","89"},{"51","67"},{"89","91"}
            ,{"91","77"},{"52","38"}};
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

    public Long saveInitData(){
        WeatherGetJsonArray weatherGetJsonArray = new WeatherGetJsonArray();

        JSONArray jsonArray = weatherGetJsonArray.getJsonArray(url);

        WeatherSaveRequestDto requestDto = new WeatherSaveRequestDto();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject weatherObject = (JSONObject) jsonArray.get(i);
            requestDto.setBaseDate((String) weatherObject.get("baseDate"));
            requestDto.setBaseTime((String) weatherObject.get("baseTime"));
            requestDto.setCategory((String) weatherObject.get("category"));
            requestDto.setFcstDate((String) weatherObject.get("fcstDate"));
            requestDto.setFcstTime((String) weatherObject.get("fcstTime"));
            requestDto.setFcstValue((String) weatherObject.get("fcstValue"));
            requestDto.setNx((Long) weatherObject.get("nx"));
            requestDto.setNy((Long)weatherObject.get("ny"));

            weatherRepository.save(requestDto.toEntity());
        }
        return null;
    }
//    private static String getJsonObjectString(BufferedReader br)
//            throws MalformedURLException, IOException, ParseException {
//
//
//
//        JSONObject outputJsonObj = new JSONObject();
//        outputJsonObj.put("status", jsonObj.get("status").toString());
//        outputJsonObj.put("data", ((JSONArray) jsonObj.get("data")).get(0));
//        outputJsonObj.put("message", jsonObj.get("message"));
//
//        String outputString = outputJsonObj.toJSONString();
//
//        return outputString;
//    }
}
