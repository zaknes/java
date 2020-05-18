package com.zaknesler;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Authenticator;
import okhttp3.Route;
import org.json.JSONObject;
import javax.swing.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        JFrame frame = new JFrame("Nest Smoke Alarm");

        frame.setContentPane(new GUI().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public static JSONObject getData() throws IOException
    {
        String auth = "Bearer [Redacted]";

        OkHttpClient client = new OkHttpClient.Builder()
            .authenticator(new Authenticator() {
                @Override public Request authenticate(Route route, Response response) throws IOException {
                    return response.request().newBuilder().header("Authorization", auth).build();
                }
            })
            .followRedirects(true)
            .followSslRedirects(true)
            .build();

        Request request = new Request.Builder()
            .url("https://developer-api.nest.com")
            .get()
            .addHeader("content-type", "application/json; charset=UTF-8")
            .addHeader("authorization", auth)
            .build();

        try {
            return new JSONObject(client.newCall(request).execute().body().string())
                .getJSONObject("devices")
                .getJSONObject("smoke_co_alarms")
                .getJSONObject("[Redacted]");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }
}
