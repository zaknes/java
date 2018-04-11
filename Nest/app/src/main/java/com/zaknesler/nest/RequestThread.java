package com.zaknesler.nest;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class RequestThread extends Thread implements Runnable
{
    private final String url = "https://developer-api.nest.com";
    private final String token = "c.z8QXCG7bHYpvTe849sb6YCjc1ssvu6GXKlRPzwSGuwPIWw5MOblgP66wEvmqBp3NSUSoXkuXAgDsr6wqCsukEatXRlUQPpMMJuzRVVxbaodyc494FbaRF0FO7EFynqGfeqqKPFkPrNpSj1D8";

    private volatile String response;

    public String getResponse()
    {
        return this.response;
    }

    @Override
    public void run()
    {
        // Use OkHttp to send a request.
        OkHttpClient client = new OkHttpClient().newBuilder()
                .authenticator(new Authenticator() {
                    @Nullable
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException
                    {
                        return response.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + token)
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
                .addHeader("Authorization", "Bearer " + token)
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
