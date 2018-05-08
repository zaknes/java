package com.zaknesler.barcodescanner.graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.android.gms.vision.barcode.Barcode;
import com.zaknesler.barcodescanner.camera.GraphicOverlay;

public class BarcodeGraphic extends GraphicOverlay.Graphic
{
    private int id;

    private static final int COLOR_CHOICES[] = {
            Color.BLUE,
            Color.GREEN,
            Color.CYAN
    };

    private static int currentColor = 0;

    private Paint paint;

    private volatile Barcode barcode;

    BarcodeGraphic(GraphicOverlay overlay)
    {
        super(overlay);

        currentColor = (currentColor + 1) % COLOR_CHOICES.length;
        final int selectedColor = COLOR_CHOICES[currentColor];

        paint = new Paint();
        paint.setColor(selectedColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(5.0f);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Barcode getBarcode()
    {
        return barcode;
    }

    void updateItem(Barcode barcode)
    {
        this.barcode = barcode;

        postInvalidate();
    }

    @Override
    public void draw(Canvas canvas)
    {
        if (barcode == null) {
            return;
        }

        float padding = 25.0f;

        RectF rect = new RectF(barcode.getBoundingBox());

        rect.left = translateX(rect.left - padding);
        rect.top = translateY(rect.top - padding);
        rect.right = translateX(rect.right + padding);
        rect.bottom = translateY(rect.bottom + padding);

        canvas.drawRoundRect(rect, 5.0f, 5.0f, paint);
    }
}