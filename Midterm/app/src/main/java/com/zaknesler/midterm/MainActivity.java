package com.zaknesler.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
    private TextView input;
    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        list = findViewById(R.id.list);

        list.setAdapter(null);
    }

    public void handleButtonPress(View view)
    {
        String query = input.getText().toString().trim();

        if (query.isEmpty()) {
            return;
        }

        RequestThread thread = new RequestThread(query);
        thread.start();

        try {
            thread.join();

            handleResponse(thread.getResponse());
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } catch (JSONException exception) {}
    }

    private void handleResponse(String response) throws JSONException
    {
        JSONObject object = (new JSONObject(response)).getJSONArray("response").getJSONObject(0);

        if (object.getJSONArray("items").length() == 0) {
            displayError("No results found.");

            return;
        }

        WordAdapter adapter = new WordAdapter(this, toStringArray(object.getJSONArray("items")));
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    private void displayError(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private String[] toStringArray(JSONArray array)
    {
        if (array == null) {
            return null;
        }

        String[] items = new String[array.length()];

        for (int i = 0; i < items.length; i++) {
            items[i] = array.optString(i);
        }

        return items;
    }
}
