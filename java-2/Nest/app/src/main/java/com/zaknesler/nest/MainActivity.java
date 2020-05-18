package com.zaknesler.nest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
    private TextView name, status, smoke, co, battery;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        status = findViewById(R.id.status);
        smoke = findViewById(R.id.smoke_state);
        co = findViewById(R.id.co_state);
        battery = findViewById(R.id.battery_state);

        this.makeCall();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.refresh) {
            this.makeCall();

            Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleResponse(String response) throws JSONException
    {
        if (response == null) {
            Toast.makeText(this, "Could not fetch data.", Toast.LENGTH_LONG).show();

            return;
        }

        JSONObject object = new JSONObject(response);

        name.setText(object.getString("name"));
        status.setText(object.getBoolean("is_online") ? "Online" : "Offline");

        smoke.setText(object.getString("smoke_alarm_state").toUpperCase());
        co.setText(object.getString("co_alarm_state").toUpperCase());
        battery.setText(object.getString("battery_health").toUpperCase());
    }

    private void makeCall()
    {
        RequestThread thread = new RequestThread();
        thread.start();

        try {
            thread.join();

            handleResponse(thread.getResponse());
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } catch (JSONException exception) {}
    }
}
