package com.zaknesler.barcodescanner.graphics;

import android.content.Context;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;
import com.zaknesler.barcodescanner.graphics.BarcodeGraphic;
import com.zaknesler.barcodescanner.graphics.BarcodeGraphicTracker;
import com.zaknesler.barcodescanner.graphics.GraphicOverlay;

public class BarcodeTrackerFactory implements MultiProcessor.Factory<Barcode>
{
    private GraphicOverlay<BarcodeGraphic> graphicOverlay;
    private Context mContext;

    public BarcodeTrackerFactory(GraphicOverlay<BarcodeGraphic> graphicOverlay, Context mContext)
    {
        this.graphicOverlay = graphicOverlay;
        this.mContext = mContext;
    }

    @Override
    public Tracker<Barcode> create(Barcode barcode)
    {
        BarcodeGraphic graphic = new BarcodeGraphic(graphicOverlay);

        return new BarcodeGraphicTracker(graphicOverlay, graphic, mContext);
    }

}

