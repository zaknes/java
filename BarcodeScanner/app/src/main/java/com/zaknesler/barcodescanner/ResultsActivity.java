package com.zaknesler.barcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.zaknesler.barcodescanner.display.SearchListAdapter;
import com.zaknesler.barcodescanner.thread.RequestThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

public class ResultsActivity extends AppCompatActivity
{
    private static String barcode;

    private RecyclerView list;

    private static Dotenv env;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search Results");

        barcode = getIntent().getStringExtra("barcode");

        list = findViewById(R.id.list);
        list.setAdapter(new SearchListAdapter(this, null));

        env = Dotenv.configure().directory("/assets").filename("env").load();

        if (getIntent().getStringExtra("action").equals("walmart")) {
            walmartRequest();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
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

        if (object.getInt("numItems") == 0) {
            Toast.makeText(this, object.getString("message"), Toast.LENGTH_LONG).show();

            return;
        }

        SearchListAdapter adapter = new SearchListAdapter(this, toStringArray(object.getJSONArray("items")));
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    private void walmartRequest()
    {

        try {
            handleResponse(runThread("http://api.walmartlabs.com/v1/search?apiKey=" + env.get("walmart_api_key") + "&query=" + barcode));
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
