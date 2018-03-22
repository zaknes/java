package com.zaknesler.wunderground;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
    protected EditText city, state;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.city_input);
        state = findViewById(R.id.state_input);
    }

    public void handleButtonPress(View view) {
        runThread(parseInput(state), parseInput(city));
    }

    private void runThread(String state, String city)
    {
        WeatherRequest thread = new WeatherRequest(state, city);

        thread.start();

        try {
            thread.join();

            validateResponse(thread.getResponse());
        } catch (InterruptedException exception) {
            displayError("Something went wrong.");
        } catch (JSONException exception) {}
    }

    private void validateResponse(String response) throws JSONException
    {
        JSONObject object = new JSONObject(response);

        System.out.println(response);

        if (object.getJSONObject("response").has("error")) {
            displayError(object.getJSONObject("response").getJSONObject("error").getString("description"));

            return;
        }

        if (object.getJSONObject("response").has("results")) {
            displayError("Multiple locations found.");

            return;
        }

        Intent weatherDisplay = new Intent(this, DisplayActivity.class);

        weatherDisplay.putExtra("data", object.getJSONObject("current_observation").toString());

        startActivity(weatherDisplay);
    }

    private String parseInput(EditText view)
    {
        String text = view.getText()
                .toString()
                .trim()
                .replace(' ', '_');

        if (text.isEmpty()) {
            return null;
        }

        return text;
    }

    private void displayError(String message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
