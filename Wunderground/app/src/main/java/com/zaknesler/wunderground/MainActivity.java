package com.zaknesler.wunderground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
{
    protected TextView city, state;

    protected OkHttpClient client;

    protected String conditionsUrl = "http://api.wunderground.com/api/7787bf91089d35a6/conditions/q/%s/%s.json";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.city_input);
        state = findViewById(R.id.state_input);

        client = new OkHttpClient();
    }

    public void getWeather(View view)
    {
        getResponse();
    }

    private String getValue(TextView view)
    {
        String text = view.getText().toString();

        if (text.isEmpty()) {
            return null;
        }

        return text;
    }

    private void handleResponse(Response response)
    {
        try {
            JSONObject object = new JSONObject(response.body().string());

            city.setText(object.getJSONObject("current_observation").getString("temperature_string"));
        } catch (Exception exception) {
            Toast.makeText(getApplicationContext(), "Unable to parse response.", Toast.LENGTH_LONG).show();
        }
    }

    private void getResponse()
    {
        Request request = new Request.Builder()
                .url(String.format(conditionsUrl, getValue(state), getValue(city)))
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Unable to get weather.", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handleResponse(response);
                    }
                });
            }
        });
    }
}
