package com.zaknesler.nest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void handleResponse(String response) throws JSONException
    {
        JSONObject object = new JSONObject(response);
    }

    public void handleButtonPress(View view)
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
