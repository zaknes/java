package com.zaknesler.wunderground;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherThread extends Thread implements Runnable
{
    private String state, city;

    private JSONObject response;

    private String url = "http://api.wunderground.com/api/7787bf91089d35a6/conditions/q/%s/%s.json";

    public WeatherThread()
    {
        //
    }

    public JSONObject getResponse()
    {
        return this.response;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public void setCity(String city)
    {
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
            Response response = client.newCall(request).execute();

            parseResponse(response);
        } catch (IOException exception) {
            this.response = null;
        }
    }

    private void parseResponse(Response response)
    {
        try {
            JSONObject object = new JSONObject(response.body().string());

            this.response = object;
        } catch (Exception exception) {
            this.response = null;
        }
    }
}
