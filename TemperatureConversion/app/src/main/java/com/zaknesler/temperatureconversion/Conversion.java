package com.zaknesler.temperatureconversion;

public class Conversion
{
    private double value;

    private String units;

    public Conversion convert(double value, String units)
    {
        this.value = value;
        this.units = units;

        return this;
    }

    public double toCelsius()
    {
        if (this.units.equalsIgnoreCase("F")) {
            return fahrenheitToCelsius(this.value);
        }

        if (this.units.equalsIgnoreCase("K")) {
            return kelvinToCelsius(this.value);
        }

        return this.value;
    }

    public double toFahrenheit()
    {
        if (this.units.equalsIgnoreCase("C")) {
            return celsiusToFahrenheit(this.value);
        }

        if (this.units.equalsIgnoreCase("K")) {
            return kelvinToFahrenheit(this.value);
        }

        return this.value;
    }

    public double toKelvin()
    {
        if (this.units.equalsIgnoreCase("C")) {
            return celsiusToKelvin(this.value);
        }

        if (this.units.equalsIgnoreCase("F")) {
            return fahrenheitToKelvin(this.value);
        }

        return this.value;
    }

    private double celsiusToFahrenheit(double celsius)
    {
        return (celsius * (9 / 5)) + 32;
    }

    private double celsiusToKelvin(double celsius)
    {
        return celsius + 273.15;
    }

    private double fahrenheitToCelsius(double fahrenheit)
    {
        return (fahrenheit - 32) * (5 / 9);
    }

    private double fahrenheitToKelvin(double fahrenheit)
    {
        return fahrenheitToCelsius(fahrenheit) + 273.15;
    }

    private double kelvinToCelsius(double kelvin)
    {
        return kelvin - 273.15;
    }

    private double kelvinToFahrenheit(double kelvin)
    {
        return (kelvin - 273.15) * (9 / 5) + 32;
    }
}
