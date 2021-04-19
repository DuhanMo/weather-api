package com.duhan.weather.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherGetJsonArray {
    public JSONArray getJsonArray(String urlStr) {
        JSONArray parse_item = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

            JSONParser jsonPar = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonPar.parse(br);

            JSONObject parse_response = (JSONObject) jsonObj.get("response");
            JSONObject parse_body = (JSONObject) parse_response.get("body");// response 로 부터 body 찾아오기
            JSONObject parse_items = (JSONObject) parse_body.get("items");// body 로 부터 items 받아오기
            parse_item = (JSONArray) parse_items.get("item");// items로 부터 itemlist 를 받아오기 itemlist : 뒤에 [ 로 시작하므로 jsonarray이다.

        } catch (Exception e) {
            e.printStackTrace();
        }
        return parse_item;
    }
}
