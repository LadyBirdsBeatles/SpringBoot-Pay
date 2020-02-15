package com.xiaochen.beatles.service.impl;


import com.xiaochen.beatles.service.WeatherService;
import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Service("WeatherService")
public class WeatherServiceImpl implements WeatherService {

    @Override
    @Scheduled(fixedRate = 1000)
    public String weather() {
        String string = null;
        String weather="http://www.weather.com.cn/data/sk/101270101.html";
        try {
            URL url=new URL(weather);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            string = IOUtils.toString(inputStream,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(string);
        return string;
    }
}
