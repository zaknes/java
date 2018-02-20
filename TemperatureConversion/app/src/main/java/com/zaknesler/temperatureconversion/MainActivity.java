package com.zaknesler.temperatureconversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private Conversion conversion;

    private TextView fahrenheitInput, celsiusInput, kelvinInput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fahrenheitInput = findViewById(R.id.input_fahrenheit);
        celsiusInput = findViewById(R.id.input_celsius);
        kelvinInput = findViewById(R.id.input_kelvin);

        conversion = new Conversion();

        fahrenheitInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String input = fahrenheitInput.getText().toString();

                if (input.isEmpty() || hasFocus) {
                    return;
                }

                double value = Double.parseDouble(input);

                setInputText(fahrenheitInput, value);
                setInputText(celsiusInput, conversion.convert(value, "F").toCelsius());
                setInputText(kelvinInput, conversion.convert(value, "F").toKelvin());
            }
        });

        celsiusInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String input = celsiusInput.getText().toString();

                if (input.isEmpty() || hasFocus) {
                    return;
                }

                double value = Double.parseDouble(input);

                setInputText(fahrenheitInput, conversion.convert(value, "C").toFahrenheit());
                setInputText(celsiusInput, value);
                setInputText(kelvinInput, conversion.convert(value, "C").toKelvin());
            }
        });

        kelvinInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String input = kelvinInput.getText().toString();

                if (input.isEmpty() || hasFocus) {
                    return;
                }

                double value = Double.parseDouble(input);
                
                setInputText(fahrenheitInput, conversion.convert(value, "K").toFahrenheit());
                setInputText(celsiusInput, conversion.convert(value, "K").toCelsius());
                setInputText(kelvinInput, value);
            }
        });
    }

    private void setInputText(TextView input, Double value)
    {
        input.setText(Double.toString(round(value)));
    }

    private double round(double value)
    {
        return Math.round(value * 100.0) / 100.0;
    }
}
