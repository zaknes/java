package com.zaknesler.bmicalculator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private TextView heightValue, weightValue, resultValue, resultDescription;
    private Spinner spinner;
    private double finalBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        heightValue = findViewById(R.id.heightValue);
        weightValue = findViewById(R.id.weightValue);
        resultValue = findViewById(R.id.resultValue);
        resultDescription = findViewById(R.id.resultDescription);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                heightValue.setText("");
                weightValue.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        findViewById(R.id.calculateButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (weightValue.getText().toString().isEmpty() || heightValue.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Values must not be empty.", Toast.LENGTH_LONG).show();

                    return;
                }

                try {
                    calculate();
                } catch (Exception exception) {
                    Toast.makeText(getBaseContext(), "Invalid input.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    protected void calculate()
    {
        String[] items = getResources().getStringArray(R.array.units);

        String selected = items[spinner.getSelectedItemPosition()];

        if (selected.equalsIgnoreCase(items[0])) {
            finalBMI = calculateMetricBMI(stringToDouble(weightValue), stringToDouble(heightValue));
        }

        if (selected.equalsIgnoreCase(items[1])) {
            finalBMI = calculateImperialBMI(stringToDouble(weightValue), stringToDouble(heightValue));
        }

        displayResults(finalBMI);
    }

    protected void displayResults(double value)
    {
        hideKeyboard();

        resultValue.setText(getString(R.string.result_value, Double.toString(value)));

        if (value < 18.5) {
            resultDescription.setText(getString(R.string.result_description_underweight));
        }

        if ((value >= 18.5) && (value <= 24.9)) {
            resultDescription.setText(getString(R.string.result_description_normal));
        }

        if ((value >= 25.0) && (value <= 29.9)) {
            resultDescription.setText(getString(R.string.result_description_overweight));
        }

        if (value > 30) {
            resultDescription.setText(getString(R.string.result_description_obese));
        }
    }

    protected void hideKeyboard()
    {
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    protected double stringToDouble(TextView value)
    {
        return round(Double.valueOf(value.getText().toString()));
    }

    protected double calculateMetricBMI(double weight, double height)
    {
        return round(weight / Math.pow(height, 2));
    }

    protected double calculateImperialBMI(double weight, double height)
    {
        return round((weight * 703) / Math.pow(height, 2));
    }

    protected double round(double value)
    {
        return Math.round(value * 10.0) / 10.0;
    }
}
