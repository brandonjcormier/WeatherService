package com.weatherservice.weatherservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherservice.weatherservice.model.WeatherApiResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class WeatherModelConvertionTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testConversion() throws IOException {
        WeatherApiResponse responseObject = mapper.readValue(getResponse(), WeatherApiResponse.class);

        Integer actualTempF = responseObject.getCurrent().getTempF();
        Integer expectedTempF = new Integer  //.valueOf("70.0");

        assertEquals(expectedTempF, actualTempF);
    }

    private String getResponse() {
        return "{\n" +
                "    \"location\": {\n" +
                "        \"name\": \"Minneapolis\",\n" +
                "        \"region\": \"Minnesota\",\n" +
                "        \"country\": \"USA\",\n" +
                "        \"lat\": 44.95,\n" +
                "        \"lon\": -93.38,\n" +
                "        \"tz_id\": \"America/Chicago\",\n" +
                "        \"localtime_epoch\": 1633994234,\n" +
                "        \"localtime\": \"2021-10-11 18:17\"\n" +
                "    },\n" +
                "    \"current\": {\n" +
                "        \"last_updated_epoch\": 1633989600,\n" +
                "        \"last_updated\": \"2021-10-11 17:00\",\n" +
                "        \"temp_c\": 21.1,\n" +
                "        \"temp_f\": 70.0,\n" +
                "        \"is_day\": 1,\n" +
                "        \"condition\": {\n" +
                "            \"text\": \"Partly cloudy\",\n" +
                "            \"icon\": \"//cdn.weatherapi.com/weather/64x64/day/116.png\",\n" +
                "            \"code\": 1003\n" +
                "        },\n" +
                "        \"wind_mph\": 8.1,\n" +
                "        \"wind_kph\": 13.0,\n" +
                "        \"wind_degree\": 350,\n" +
                "        \"wind_dir\": \"N\",\n" +
                "        \"pressure_mb\": 1005.0,\n" +
                "        \"pressure_in\": 29.68,\n" +
                "        \"precip_mm\": 0.0,\n" +
                "        \"precip_in\": 0.0,\n" +
                "        \"humidity\": 44,\n" +
                "        \"cloud\": 75,\n" +
                "        \"feelslike_c\": 21.1,\n" +
                "        \"feelslike_f\": 70.0,\n" +
                "        \"vis_km\": 16.0,\n" +
                "        \"vis_miles\": 9.0,\n" +
                "        \"uv\": 6.0,\n" +
                "        \"gust_mph\": 7.8,\n" +
                "        \"gust_kph\": 12.6\n" +
                "    }\n" +
                "}";
    }
}
