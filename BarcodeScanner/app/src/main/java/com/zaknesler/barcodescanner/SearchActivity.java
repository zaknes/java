package com.zaknesler.barcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity
{
    private static String barcode;

    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        list = findViewById(R.id.list);
        list.setAdapter(null);

        barcode = getIntent().getStringExtra("barcode");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(barcode);

        walmartRequest();
    }

    private void handleResponse(String response) throws JSONException
    {
        if (response == null) {
            return;
        }

        JSONObject object = new JSONObject(response);

        if (object.has("errors")) {
            Toast.makeText(this, object.getJSONArray("errors").getJSONObject(0).getString("message"), Toast.LENGTH_LONG).show();

            return;
        }

        ListAdapter adapter = new ListAdapter(this, toStringArray(object.getJSONArray("items")));
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    private void walmartRequest()
    {
        try {
            handleResponse(runThread("http://api.walmartlabs.com/v1/items?apiKey=kr2f3cr5zuraxm9d5vpxy2f5&upc=" + barcode));
        } catch (JSONException exception) {}
    }

    private String runThread(String url)
    {
        RequestThread thread = new RequestThread(url);

        thread.start();

        try {
            thread.join();

            return thread.getResponse();
        } catch (InterruptedException exception) {
            Log.e("Walmart Thread Exception", exception.getMessage());
        }

        return null;
    }

    // From Stack Overflow.
    // Converts a JSONArray to a regular string array.
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
