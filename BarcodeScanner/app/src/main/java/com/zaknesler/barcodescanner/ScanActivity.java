package com.zaknesler.barcodescanner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.zaknesler.barcodescanner.graphics.BarcodeGraphic;
import com.zaknesler.barcodescanner.graphics.BarcodeGraphicTracker;
import com.zaknesler.barcodescanner.graphics.BarcodeTrackerFactory;
import com.zaknesler.barcodescanner.camera.GraphicOverlay;

import java.io.IOException;

public class ScanActivity extends AppCompatActivity implements BarcodeGraphicTracker.BarcodeUpdateListener
{
    private SurfaceView cameraView;

    private CameraSource cameraSource;
    private GraphicOverlay<BarcodeGraphic> graphicOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        getSupportActionBar().hide();

        cameraView = findViewById(R.id.camera_view);

        initializeScanner();
    }

    private void initializeScanner()
    {
        BarcodeDetector detector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        BarcodeTrackerFactory trackerFactory = new BarcodeTrackerFactory(graphicOverlay, getApplicationContext());

        detector.setProcessor(new MultiProcessor.Builder<>(trackerFactory).build());

        if (!detector.isOperational()) {
            Log.e(getString(R.string.app_name), "Could not start detector.");
        }

        cameraSource = new CameraSource.Builder(getApplicationContext(), detector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(30.0f)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1920, 1080)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder)
            {
                startCameraSource();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(SurfaceHolder holder)
            {
                cameraSource.stop();
            }
        });

    }

    private void startCameraSource()
    {
        if (ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            Log.e(getString(R.string.app_name), "Camera permission not granted.");
            return;
        }

        try {
            cameraSource.start(cameraView.getHolder());
        } catch (IOException exception) {
            Log.e(getString(R.string.app_name), exception.getMessage());
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        startCameraSource();
    }

    @Override
    public void onBarcodeDetected(Barcode barcode)
    {
        Log.i(getString(R.string.app_name), barcode.displayValue);
    }
}
