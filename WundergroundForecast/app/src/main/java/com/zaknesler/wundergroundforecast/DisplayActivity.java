package com.zaknesler.wundergroundforecast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

public class DisplayActivity extends AppCompatActivity
{
    private View[] views = new View[5];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        views = new View[] {
            findViewById(R.id.item_1),
            findViewById(R.id.item_2),
            findViewById(R.id.item_3),
            findViewById(R.id.item_4),
            findViewById(R.id.item_5),
        };

        try {
            display(new JSONArray(getIntent().getStringExtra("data")));
        } catch (JSONException exception) {}
    }

    private void display(JSONArray data) throws JSONException
    {
        getSupportActionBar().setTitle(R.string.forecast);

        for (int i = 0; i < 5; i++) {
            JSONObject current = data.getJSONObject(i);

            ((ImageView) views[i].findViewById(R.id.image)).setImageResource(getResources().getIdentifier("ic_" + current.getString("icon"), "drawable", getPackageName()));
            ((TextView) views[i].findViewById(R.id.weekday)).setText(current.getJSONObject("date").getString("weekday"));
            ((TextView) views[i].findViewById(R.id.conditions)).setText(current.getString("conditions"));
            ((TextView) views[i].findViewById(R.id.high)).setText(getString(R.string.temperature, current.getJSONObject("high").getString("fahrenheit")));
            ((TextView) views[i].findViewById(R.id.low)).setText(getString(R.string.temperature, current.getJSONObject("low").getString("fahrenheit")));
        }
    }
}
