package com.zaknesler.barcodescanner;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.zaknesler.barcodescanner.camera.CameraSourcePreview;
import com.zaknesler.barcodescanner.graphics.BarcodeGraphic;
import com.zaknesler.barcodescanner.graphics.BarcodeGraphicTracker;
import com.zaknesler.barcodescanner.graphics.BarcodeTrackerFactory;
import com.zaknesler.barcodescanner.graphics.GraphicOverlay;

import java.io.IOException;

public final class MainActivity extends Activity implements BarcodeGraphicTracker.BarcodeUpdateListener
{
    private static final String TAG = "Barcode Scanner";

    private static final int RC_HANDLE_CAMERA_PERM = 2;

    private CameraSource cameraSource;
    private CameraSourcePreview cameraPreview;
    private GraphicOverlay<BarcodeGraphic> graphicOverlay;

    private GestureDetector gestureDetector;

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        cameraPreview = findViewById(R.id.preview);
        graphicOverlay = findViewById(R.id.graphicOverlay);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            createCameraSource();
        } else {
            requestCameraPermission();
        }

        gestureDetector = new GestureDetector(this, new CaptureGestureListener());
    }

    private void requestCameraPermission()
    {
        Log.w(TAG, "Camera permission is not granted. Requesting permission");

        final String[] permissions = new String[]{Manifest.permission.CAMERA};

        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM);

            return;
        }

        final Activity thisActivity = this;

        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ActivityCompat.requestPermissions(thisActivity, permissions, RC_HANDLE_CAMERA_PERM);
            }
        };

        findViewById(R.id.topLayout).setOnClickListener(listener);

        Snackbar.make(graphicOverlay, R.string.permission_camera_rationale,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, listener)
                .show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        return super.onTouchEvent(e) || gestureDetector.onTouchEvent(e);
    }

    private void createCameraSource()
    {
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(getApplicationContext()).build();

        BarcodeTrackerFactory barcodeFactory = new BarcodeTrackerFactory(graphicOverlay, this);

        barcodeDetector.setProcessor(new MultiProcessor.Builder<>(barcodeFactory).build());

        cameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1600, 1024)
                .setAutoFocusEnabled(true)
                .setRequestedFps(30.0f)
                .build();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        startCameraSource();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        if (cameraPreview != null) {
            cameraPreview.stop();
        }
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (cameraPreview != null) {
            cameraPreview.release();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode != RC_HANDLE_CAMERA_PERM) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            return;
        }

        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            createCameraSource();

            return;
        }

        new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                finish();
            }
        };
    }

    private void startCameraSource() throws SecurityException
    {
        if (cameraSource == null) {
            return;
        }

        try {
            cameraPreview.start(cameraSource, graphicOverlay);

            Log.i(TAG, "Attempting to start camera preview.");
        } catch (IOException e) {
            Log.e(TAG, "Unable to start camera source.", e);

            cameraSource.release();
            cameraSource = null;
        }
    }

    private boolean onTap(float rawX, float rawY)
    {
        int[] location = new int[2];

        graphicOverlay.getLocationOnScreen(location);

        float x = (rawX - location[0]) / graphicOverlay.getWidthScaleFactor();
        float y = (rawY - location[1]) / graphicOverlay.getHeightScaleFactor();

        Barcode best = null;

        float bestDistance = 50.0f;

        for (BarcodeGraphic graphic : graphicOverlay.getGraphics()) {
            Barcode barcode = graphic.getBarcode();

            if (barcode.getBoundingBox().contains((int) x, (int) y)) {
                best = barcode;

                break;
            }

            float dx = x - barcode.getBoundingBox().centerX();
            float dy = y - barcode.getBoundingBox().centerY();

            float distance = (dx * dx) + (dy * dy);

            if (distance < bestDistance) {
                best = barcode;
                bestDistance = distance;
            }
        }

        if (best == null) {
            return false;
        }

        launchSearchActivity(best);

        return true;
    }

    private void launchSearchActivity(Barcode barcode)
    {
        Intent intent = new Intent(this, SearchActivity.class)
                .putExtra("barcode", barcode.displayValue);

        cameraPreview.release();
        
        startActivity(intent);
    }

    private class CaptureGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            return onTap(e.getRawX(), e.getRawY()) || super.onSingleTapConfirmed(e);
        }
    }

    @Override
    public void onBarcodeDetected(Barcode barcode) {}
}
