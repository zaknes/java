package com.zaknesler.wunderground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DisplayActivity extends AppCompatActivity
{
    private JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        ((TextView) findViewById(R.id.temporary)).setText(data.toString(4));
    }
}
