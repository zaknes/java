package com.zaknesler.barcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity
{
    private TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        getSupportActionBar().setTitle("Barcode Value");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        results = findViewById(R.id.result_text);

        results.setText(getIntent().getStringExtra("barcode"));
    }
}
