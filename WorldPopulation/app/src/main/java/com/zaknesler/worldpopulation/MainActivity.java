package com.zaknesler.worldpopulation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

        TextView currentPopulationValue = findViewById(R.id.value_current_population);

        currentPopulationValue.setText(currentPopulation.toPlainString());
    }
}
