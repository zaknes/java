package com.zaknesler.wunderground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.city_input);
        state = findViewById(R.id.state_input);
    }

    public void getWeather(View view)
    {
        JSONObject weather = runThread(parseInput(state), parseInput(city));

        if (weather == null) {
            Toast.makeText(getApplicationContext(), "Unable to get weather.", Toast.LENGTH_LONG).show();

            return;
        }
        
        try {
            if (weather.getJSONObject("response").has("error") && weather.getJSONObject("response").getJSONObject("error").getString("type").equalsIgnoreCase("querynotfound")) {
                Toast.makeText(getApplicationContext(), "Results not found.", Toast.LENGTH_LONG).show();

                return;
            }

            displayResults(weather.getJSONObject("current_observation"));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    private void displayResults(JSONObject weather) throws JSONException
    {
        this.setTitle(weather.getJSONObject("display_location").getString("full"));
    }

    private JSONObject runThread(String state, String city)
    {
        WeatherThread weatherThread = new WeatherThread();

        weatherThread.setState(state);
        weatherThread.setCity(city);

        weatherThread.start();

        while (true) {
            try {
                weatherThread.join();

                break;
            } catch(Exception exception) {
                exception.printStackTrace();

                return null;
            }
        }

        return weatherThread.getResponse();
    }

    private String parseInput(TextView view)
    {
        String text = view.getText().toString();

        if (text.isEmpty()) {
            return null;
        }

        return text;
    }
}
