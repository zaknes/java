package com.zaknesler.barcodescanner.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.gms.vision.CameraSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class GraphicOverlay<T extends GraphicOverlay.Graphic> extends View
{
    private final Object object = new Object();
    private Set<T> graphics = new HashSet<>();

    private int previewWidth;
    private int previewHeight;

    private float widthScaleFactor = 1.0f;
    private float heightScaleFactor = 1.0f;

    private int facing = CameraSource.CAMERA_FACING_BACK;

    public static abstract class Graphic
    {
        private GraphicOverlay overlay;

        public Graphic(GraphicOverlay overlay)
        {
            this.overlay = overlay;
        }

        public abstract void draw(Canvas canvas);

        public float scaleX(float horizontal)
        {
            return horizontal * overlay.widthScaleFactor;
        }

        public float scaleY(float vertical)
        {
            return vertical * overlay.heightScaleFactor;
        }

        public float translateX(float x)
        {
            if (overlay.facing == CameraSource.CAMERA_FACING_FRONT) {
                return overlay.getWidth() - scaleX(x);
            }

            return scaleX(x);
        }

        public float translateY(float y)
        {
            return scaleY(y);
        }

        public void postInvalidate()
        {
            overlay.postInvalidate();
        }
    }

    public GraphicOverlay(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void clear()
    {
        synchronized (object) {
            graphics.clear();
        }

        postInvalidate();
    }

    public void add(T graphic)
    {
        synchronized (object) {
            graphics.add(graphic);
        }

        postInvalidate();
    }

    public void remove(T graphic)
    {
        synchronized (object) {
            graphics.remove(graphic);
        }

        postInvalidate();
    }

    public List<T> getGraphics()
    {
        synchronized (object) {
            return new Vector(graphics);
        }
    }

    public float getWidthScaleFactor()
    {
        return widthScaleFactor;
    }


    public float getHeightScaleFactor()
    {
        return heightScaleFactor;
    }

    public void setCameraInfo(int previewWidth, int previewHeight, int facing)
    {
        synchronized (object) {
            this.previewWidth = previewWidth;
            this.previewHeight = previewHeight;
            this.facing = facing;
        }

        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        synchronized (object) {
            if ((previewWidth != 0) && (previewHeight != 0)) {
                widthScaleFactor = (float) canvas.getWidth() / (float) previewWidth;
                heightScaleFactor = (float) canvas.getHeight() / (float) previewHeight;
            }

            for (Graphic graphic : graphics) {
                graphic.draw(canvas);
            }
        }
    }
}
