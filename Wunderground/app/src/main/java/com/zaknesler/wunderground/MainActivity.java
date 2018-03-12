package com.zaknesler.wunderground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
{
    protected TextView city, state;

    protected OkHttpClient client = new OkHttpClient();

    protected String conditionsUrl = "http://api.wunderground.com/api/7787bf91089d35a6/conditions/q/%s/%s.json";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.city_input);
        state = findViewById(R.id.state_input);
    }

    protected void getWeather(View view)
    {
        try {
            JSONObject response = get(String.format(conditionsUrl, getValue(city), getValue(state)));

            System.out.println(response);
        } catch (Exception exception) {

        }
    }

    private String getValue(TextView view)
    {
        String text = view.getText().toString();

        if (text.isEmpty()) {
            return null;
        }

        return text;
    }

    private JSONObject get(String url) throws IOException, JSONException
    {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        return new JSONObject(response.body().string());
    }
}
