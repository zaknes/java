package com.zaknesler.wundergroundconditions;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class WeatherRequest extends Thread implements Runnable
{
    private volatile String response;

    private final String url = "http://api.wunderground.com/api/7787bf91089d35a6/conditions/q/%s/%s.json";

    private String state, city;

    public WeatherRequest(String state, String city)
    {
        this.state = state;
        this.city = city;
    }

    @Override
    public void run()
    {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format(this.url, this.state, this.city))
                .build();

        try {
            this.response = client.newCall(request)
                    .execute()
                    .body()
                    .string();
        } catch (IOException exception) {
            this.response = null;
        }
    }

    public String getResponse()
    {
        return this.response;
    }
}
