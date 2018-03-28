package com.zaknesler.wunderground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

public class DisplayActivity extends AppCompatActivity
{
    private JSONObject data;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        parseData(getIntent().getStringExtra("data"));

        image = findViewById(R.id.image);
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

//        image.setImageResource(getResources().getIdentifier("unknown", "drawable", getPackageName()));

//        image.setImageResource(getResources().getIdentifier("sleet.gif", "drawable", getPackageName()));
    }
}
