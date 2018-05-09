package com.zaknesler.barcodescanner.graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import com.google.android.gms.vision.barcode.Barcode;

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

    BarcodeGraphic(GraphicOverlay overlay) {
        super(overlay);

        currentColor = (currentColor + 1) % COLOR_CHOICES.length;
        final int selectedColor = COLOR_CHOICES[currentColor];

        paint = new Paint();

        paint.setColor(selectedColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10.0f);
    }

    @Override
    public void draw(Canvas canvas)
    {
        Barcode barcode = this.barcode;

        if (barcode == null) {
            return;
        }

        RectF rect = new RectF(barcode.getBoundingBox());

        float padding = 25.0f;

        rect.left = translateX(rect.left - padding);
        rect.top = translateY(rect.top - padding);
        rect.right = translateX(rect.right + padding);
        rect.bottom = translateY(rect.bottom + padding);

        canvas.drawRect(rect, paint);
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
}
