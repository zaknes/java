package com.zaknesler.barcodescanner.graphics;

import android.content.Context;
import android.support.annotation.UiThread;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

public class BarcodeGraphicTracker extends Tracker<Barcode>
{
    private GraphicOverlay<BarcodeGraphic> graphicOverlay;
    private BarcodeGraphic graphic;

    private BarcodeUpdateListener barcodeUpdateListener;


    public interface BarcodeUpdateListener
    {
        @UiThread
        void onBarcodeDetected(Barcode barcode);
    }

    BarcodeGraphicTracker(GraphicOverlay<BarcodeGraphic> graphicOverlay, BarcodeGraphic graphic, Context context)
    {
        this.graphicOverlay = graphicOverlay;
        this.graphic = graphic;
        this.barcodeUpdateListener = (BarcodeUpdateListener) context;
    }

    @Override
    public void onNewItem(int id, Barcode item)
    {
        graphic.setId(id);
        barcodeUpdateListener.onBarcodeDetected(item);
    }

    @Override
    public void onUpdate(Detector.Detections<Barcode> detectionResults, Barcode item)
    {
        graphicOverlay.add(graphic);
        graphic.updateItem(item);
    }

    @Override
    public void onMissing(Detector.Detections<Barcode> detectionResults)
    {
        graphicOverlay.remove(graphic);
    }

    @Override
    public void onDone()
    {
        graphicOverlay.remove(graphic);
    }
}
