package com.zaknesler.worldpopulation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
{
    private static double growthRate = 0.0109;
    private static long currentPopulation = 7601714600L;

    private static DecimalFormat formatter = new DecimalFormat("#,###,###,###");

    private TextView currentPopulationValue, growthRateValue, yearInput, resultTop, resultBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentPopulationValue = findViewById(R.id.value_current_population);
        growthRateValue = findViewById(R.id.value_growth_rate);
        yearInput = findViewById(R.id.input_year);
        resultTop = findViewById(R.id.result_top);
        resultBottom = findViewById(R.id.result_bottom);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                resultTop.setText(getResources().getString(
                        R.string.result_top,
                        getYear()
                ));

                resultBottom.setText(getResources().getString(
                        R.string.result_bottom,
                        formatter.format(calculateWorldPopulation(getYearDifference(getYear())))
                ));
            }
        });

        displayInformation();
    }

    private long getYear()
    {
        try {
            return Long.valueOf(yearInput.getText().toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    private long calculateWorldPopulation(long difference)
    {
        return (long) (currentPopulation * Math.exp(growthRate * difference));
    }

    private long getYearDifference(long check)
    {
        return check - 2018;
    }

    private void displayInformation()
    {
        currentPopulationValue.setText(formatter.format(currentPopulation));
        growthRateValue.setText(getResources().getString(
                R.string.value_growth_rate,
                Double.toString(growthRate * 100)
        ));
    }
}
