package app.handong.feed.util;


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

}
