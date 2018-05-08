package com.zaknesler.barcodescanner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
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
import com.zaknesler.barcodescanner.camera.CameraPreview;
import com.zaknesler.barcodescanner.graphics.BarcodeGraphic;
import com.zaknesler.barcodescanner.graphics.BarcodeGraphicTracker;
import com.zaknesler.barcodescanner.graphics.BarcodeTrackerFactory;
import com.zaknesler.barcodescanner.camera.GraphicOverlay;

import java.io.IOException;

public class ScanActivity extends AppCompatActivity implements BarcodeGraphicTracker.BarcodeUpdateListener
{
    private static final int CAMERA_REQUEST_CODE = 10;

    private CameraPreview cameraView;

    private CameraSource cameraSource;
    private GraphicOverlay<BarcodeGraphic> graphicOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        getSupportActionBar().hide();

        cameraView = findViewById(R.id.camera_view);

        if (hasCameraPermission()) {
            requestCameraPermission();
        }

        initializeScanner();
    }

    private void requestCameraPermission()
    {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
//            return;
//        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
    }

    private boolean hasCameraPermission()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            return false;
        }

        return true;
    }

    private void initializeScanner()
    {
        BarcodeDetector detector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        BarcodeTrackerFactory trackerFactory = new BarcodeTrackerFactory(graphicOverlay, this);

        detector.setProcessor(new MultiProcessor.Builder<>(trackerFactory).build());

        if (!detector.isOperational()) {
            Log.e(getString(R.string.app_name), "Could not start detector.");
        }

        cameraSource = new CameraSource.Builder(this, detector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(30.0f)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1920, 1080)
                .build();

    }

    private void startCameraSource() throws SecurityException
    {
        try {
            cameraView.start(cameraSource, graphicOverlay);
        } catch (IOException exception) {
            Log.e(getString(R.string.app_name), exception.getMessage());

            cameraSource.release();
            cameraSource = null;
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        initializeScanner();
    }

    @Override
    public void onBarcodeDetected(Barcode barcode)
    {
        Log.i(getString(R.string.app_name), barcode.displayValue);
    }
}
