package com.weatherservice.weatherservice.controller;


//import com.weatherapi.model.WeatherResponse;
import com.weatherservice.weatherservice.model.WeatherRequest;
import com.weatherservice.weatherservice.model.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController {

    @GetMapping("/weather")
    public WeatherResponse  weatherRequest @RequestParam(value = "zipCode", defaultValue = "96795") String zipCode {
        String response = "";

        try {
            response = sendRequest(zipCode);
        } catch (IOException e) {
            response = "Error";
            e.printStackTrace();
        }

        return new WeatherResponse(response);
    }

    public String sendRequest(String q) throws IOException {
        URL url = new URL("http://api.weatherapi.com/v1/current.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("key", "058f6f7f4553440585f220658210910");
        parameters.put("q", q);

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        return content.toString();
    }

    public class ParameterStringBuilder {
        public static String getParamsString(Map<String, String> params)
                throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                result.append("&");
            }

            String resultString = result.toString();
            return resultString.length() > 0
                    ? resultString.substring(0, resultString.length() - 1)
                    : resultString;
        }
    }

}
