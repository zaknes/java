package com.zaknesler.nest;

import android.support.annotation.Nullable;

import java.io.IOException;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class RequestThread extends Thread implements Runnable
{
    private final String url = "https://developer-api.nest.com";

    private volatile String response;

    public String getResponse()
    {
        return this.response;
    }

    @Override
    public void run()
    {
        final Dotenv env = Dotenv.configure().directory("/assets").filename("env").load();

        // Use OkHttp to send a request.
        OkHttpClient client = new OkHttpClient().newBuilder()
                .authenticator(new Authenticator() {
                    @Nullable
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException
                    {
                        return response.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + env.get("auth_token"))
                                .build();
                    }
                })
                .followRedirects(true)
                .followSslRedirects(true)
                .build();

        Request request = new Request.Builder()
                .url(this.url)
                .get()
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .addHeader("Authorization", "Bearer " + env.get("auth_token"))
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
