package com.zaknesler.barcodescanner.graphics;

import android.content.Context;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;
import com.zaknesler.barcodescanner.camera.GraphicOverlay;

public class BarcodeTrackerFactory implements MultiProcessor.Factory<Barcode>
{
    private GraphicOverlay<BarcodeGraphic> graphicOverlay;
    private Context context;

    public BarcodeTrackerFactory(GraphicOverlay<BarcodeGraphic> graphicOverlay, Context context)
    {
        this.graphicOverlay = graphicOverlay;
        this.context = context;
    }

    @Override
    public Tracker<Barcode> create(Barcode barcode)
    {
        BarcodeGraphic graphic = new BarcodeGraphic(graphicOverlay);
        return new BarcodeGraphicTracker(graphicOverlay, graphic, context);
    }

}
