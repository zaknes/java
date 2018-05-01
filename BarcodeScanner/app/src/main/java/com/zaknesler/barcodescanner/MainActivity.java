package com.zaknesler.barcodescanner;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import devliving.online.mvbarcodereader.BarcodeCaptureFragment;
import devliving.online.mvbarcodereader.MVBarcodeScanner;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        initializeScanner();
    }

    private void initializeScanner()
    {
        BarcodeCaptureFragment fragment = BarcodeCaptureFragment.instantiate(MVBarcodeScanner.ScanningMode.SINGLE_MANUAL, Barcode.ALL_FORMATS);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();

        fragment.setListener(new BarcodeCaptureFragment.BarcodeScanningListener()
        {

            @Override
            public void onBarcodeScanned(Barcode barcode)
            {
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);

                intent.putExtra("barcode", barcode.displayValue);
                startActivity(intent);
            }

            @Override
            public void onBarcodesScanned(List<Barcode> barcodes)
            {
                //
            }

            @Override
            public void onBarcodeScanningFailed(String reason)
            {
                Log.d("Barcode Scanner Fail", reason);
            }
        });
    }
}
