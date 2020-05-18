package com.zaknesler.barcodescanner.thread;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class RequestThread extends Thread implements Runnable
{
    private final String url;

    private volatile String response;

    public RequestThread(String url)
    {
        this.url = url;
    }

    public String getResponse()
    {
        return this.response;
    }

    @Override
    public void run()
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(true)
                .followSslRedirects(true)
                .build();

        Request request = new Request.Builder()
                .url(this.url)
                .get()
                .addHeader("Content-Type", "application/json; charset=UTF-8")
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
