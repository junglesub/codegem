package com.thc.realspr.util;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class WebConnect {


    public static Map stringToMap(String before) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = null;
        try {
            // convert JSON string to Map
            map = mapper.readValue(before, Map.class);
            System.out.println("map : " + map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String, Object> connect(String reqURL, Map<String,Object> params) throws IOException {
        String result =  "";
        Map<String, Object> resultMap = new HashMap<>();

        try {
            StringBuilder postData = new StringBuilder();
            for(Map.Entry<String,Object> param : params.entrySet()) {
                if(postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            System.out.println("postData : " + postData.toString());
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            URL url = new URL(reqURL + "?" + postData.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            resultMap = stringToMap(result);
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultMap;
    }
    public static Map<String, Object> getKaKaoToken(String code) throws IOException {

        String reqURL = "https://kauth.kakao.com/oauth/token";

        String result =  "";
        Map<String, Object> resultMap = new HashMap<>();

        //access_token을 이용하여 사용자 정보 조회
        try {
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("grant_type", "authorization_code");
            /*<!--주의!!! 배포 전 꼭 변경하셔야 합니다!-->*/
            params.put("client_id", "5acd019d5336587d31ba98dac48b8066");
            params.put("redirect_uri", "http://localhost:8080/tbuser/login/kakao");
            params.put("code", code);

            StringBuilder postData = new StringBuilder();
            for(Map.Entry<String,Object> param : params.entrySet()) {
                if(postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            System.out.println("postData : " + postData.toString());
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            URL url = new URL(reqURL + "?" + postData.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=utf-8"); //전송할 header 작성, access_token전송
            //conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            resultMap = stringToMap(result);
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultMap;
    }
    public static Map<String, Object> getKaKaoInfo(String access_token) throws IOException {

        String reqURL = "https://kapi.kakao.com/v2/user/me";
        //String reqURL = "https://kauth.kakao.com/oauth/token";

        String result =  "";
        Map<String, Object> resultMap = new HashMap<>();

        //access_token을 이용하여 사용자 정보 조회
        try {
            Map<String,Object> params = new HashMap<String,Object>();

            StringBuilder postData = new StringBuilder();
            for(Map.Entry<String,Object> param : params.entrySet()) {
                if(postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            System.out.println("postData : " + postData.toString());
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            URL url = new URL(reqURL + "?" + postData.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=utf-8"); //전송할 header 작성, access_token전송
            conn.setRequestProperty("Authorization", "Bearer " + access_token); //전송할 header 작성, access_token전송
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            resultMap = stringToMap(result);
            br.close();

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultMap;
    }
}
