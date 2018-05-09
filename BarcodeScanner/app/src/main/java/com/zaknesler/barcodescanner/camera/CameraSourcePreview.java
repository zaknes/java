package com.zaknesler.barcodescanner.camera;

import android.Manifest;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import com.google.android.gms.common.images.Size;
import com.google.android.gms.vision.CameraSource;
import com.zaknesler.barcodescanner.graphics.GraphicOverlay;

import java.io.IOException;

public class CameraSourcePreview extends ViewGroup
{
    private static final String TAG = "CameraSourcePreview";

    private Context context;
    private GraphicOverlay overlay;
    private SurfaceView surfaceView;
    private CameraSource cameraSource;

    private boolean startRequested;
    private boolean surfaceAvailable;

    public CameraSourcePreview(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.context = context;

        startRequested = false;
        surfaceAvailable = false;

        surfaceView = new SurfaceView(context);
        surfaceView.getHolder().addCallback(new SurfaceCallback());

        addView(surfaceView);
    }

    @RequiresPermission(Manifest.permission.CAMERA)
    public void start(CameraSource cameraSource) throws IOException, SecurityException
    {
        if (cameraSource == null) {
            stop();
        }

        this.cameraSource = cameraSource;

        this.startRequested = true;

        startIfReady();
    }

    @RequiresPermission(Manifest.permission.CAMERA)
    public void start(CameraSource cameraSource, GraphicOverlay overlay) throws IOException, SecurityException
    {
        this.overlay = overlay;

        start(cameraSource);
    }

    public void stop()
    {
        if (cameraSource == null) {
            return;
        }

        cameraSource.stop();
    }

    public void release() {
        if (cameraSource == null) {
            return;
        }

        cameraSource.release();
        cameraSource = null;
    }

    @RequiresPermission(Manifest.permission.CAMERA)
    private void startIfReady() throws IOException, SecurityException
    {
        if (!startRequested || !surfaceAvailable) {
            return;
        }

        if (overlay == null) {
            return;
        }

        cameraSource.start(surfaceView.getHolder());

        Size size = cameraSource.getPreviewSize();

        int min = Math.min(size.getWidth(), size.getHeight());
        int max = Math.max(size.getWidth(), size.getHeight());

        if (isPortraitMode()) {
            overlay.setCameraInfo(min, max, cameraSource.getCameraFacing());
        } else {
            overlay.setCameraInfo(max, min, cameraSource.getCameraFacing());
        }

        overlay.clear();
        startRequested = false;
    }

    private class SurfaceCallback implements SurfaceHolder.Callback
    {
        @Override
        public void surfaceCreated(SurfaceHolder surface)
        {
            surfaceAvailable = true;

            try {
                startIfReady();
            } catch (SecurityException se) {
                Log.e(TAG,"Do not have permission to start the camera", se);
            } catch (IOException e) {
                Log.e(TAG, "Could not start camera source.", e);
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surface)
        {
            surfaceAvailable = false;
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        if (cameraSource == null) {
            return;
        }

        int width = 320;
        int height = 240;

        Size size = cameraSource.getPreviewSize();

        if (size != null) {
            width = size.getWidth();
            height = size.getHeight();
        }

        if (isPortraitMode()) {
            int tmp = width;

            width = height;
            height = tmp;
        }

        final int layoutWidth = right - left;
        final int layoutHeight = bottom - top;

        int childWidth = layoutWidth;
        int childHeight = (layoutWidth / width) * height;

        if (childHeight > layoutHeight) {
            childHeight = layoutHeight;

            childWidth = (layoutHeight / height) * width;
        }

        for (int i = 0; i < getChildCount(); ++i) {
            getChildAt(i).layout(0, 0, childWidth, childHeight);
        }

        try {
            startIfReady();
        } catch (SecurityException se) {
            Log.e(TAG,"Do not have permission to start the camera", se);
        } catch (IOException e) {
            Log.e(TAG, "Could not start camera source.", e);
        }
    }

    private boolean isPortraitMode()
    {
        int orientation = context.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return false;
        }

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            return true;
        }

        return false;
    }
}
