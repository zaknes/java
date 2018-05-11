package com.zaknesler.barcodescanner;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DisplayBarcodeActivity extends AppCompatActivity
{
    private static String barcode;

    private TextView barcodeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_barcode);

        barcodeInput = findViewById(R.id.barcode_input);
        barcode = getIntent().getStringExtra("barcode");

        barcodeInput.setText(barcode);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onWalmartButtonPressed(View view)
    {
        Intent intent = new Intent(this, ResultsActivity.class);

        intent.putExtra("action", "walmart");
        intent.putExtra("barcode", barcodeInput.getText().toString());

        startActivity(intent);
    }
}
