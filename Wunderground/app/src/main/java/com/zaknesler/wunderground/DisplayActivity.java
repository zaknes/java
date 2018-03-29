package com.zaknesler.wunderground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayActivity extends AppCompatActivity
{
    private TextView temperature, weather;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image = findViewById(R.id.image);
        temperature = findViewById(R.id.temperature_string);
        weather = findViewById(R.id.weather_string);

        parseData(getIntent().getStringExtra("data"));
    }

    private void parseData(String data)
    {
        try {
            display(new JSONObject(data));
        } catch (JSONException exception) {}
    }

    private void display(JSONObject data) throws JSONException
    {
        getSupportActionBar().setTitle(data.getJSONObject("display_location").getString("full"));

        image.setImageResource(getResources().getIdentifier("ic_" + data.getString("icon"), "drawable", getPackageName()));

        temperature.setText(getResources().getString(R.string.temperature, data.getString("temp_f")));

        weather.setText(data.getString("weather"));
    }
}
