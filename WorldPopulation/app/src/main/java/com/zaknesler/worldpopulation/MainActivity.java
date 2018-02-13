package com.zaknesler.worldpopulation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity
{
    private static double growthRate = 0.0109;
    private static BigDecimal currentPopulation = new BigDecimal("7601714600");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
