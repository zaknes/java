package com.zaknesler.midterm;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

class RequestThread extends Thread implements Runnable
{
    private String query;

    private final String url = "https://api.wordassociations.net/associations/v1.0/json/search?apikey=62c4e65e-4848-47c0-bbe4-e2315e8d2925&lang=en&text=%s";

    private volatile String response;

    public RequestThread(String query)
    {
        this.query = query;
    }

    public String getResponse()
    {
        return this.response;
    }

    @Override
    public void run()
    {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format(this.url, this.query))
                .build();

        try {
            this.response = client.newCall(request)
                    .execute()
                    .body()
                    .string();
        } catch (IOException exception) {
            this.response = null;

            exception.printStackTrace();
        }
    }
}
