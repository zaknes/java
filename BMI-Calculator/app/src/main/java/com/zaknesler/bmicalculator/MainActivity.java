package com.zaknesler.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText heightValue = findViewById(R.id.heightValue);
        final EditText weightValue = findViewById(R.id.weightValue);

        final TextView resultValue = findViewById(R.id.resultValue);
        final TextView resultDescription = findViewById(R.id.resultDescription);

        final Button button = findViewById(R.id.calculateButton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (weightValue.getText().toString().equalsIgnoreCase("") || heightValue.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getBaseContext(), "Values must not be empty.", Toast.LENGTH_LONG).show();

                    return;
                }

                double weight = stringToDouble(weightValue);
                double height = stringToDouble(heightValue);

                double result = calculateBMI(weight, height);

                resultValue.setText(formatResult(result));

                if (result < 18.5) {
                    resultDescription.setText(getString(R.string.result_description_underweight));
                }

                if ((result >= 18.5) &&  (result <= 24.9)) {
                    resultDescription.setText(getString(R.string.result_description_normal));
                }

                if (result > 24.9) {
                    resultDescription.setText(getString(R.string.result_description_overweight));
                }
            }
        });
    }

    protected String formatResult(double value)
    {
        DecimalFormat formatter = new DecimalFormat("0.00");

        return getString(R.string.result_value, formatter.format(value));
    }

    protected double stringToDouble(TextView value)
    {
        return Double.valueOf(value.getText().toString());
    }

    protected double calculateBMI(double weight, double height)
    {
        return weight / (height * height);
    }
}
