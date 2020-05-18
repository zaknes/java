package com.zaknesler;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        DecimalFormat formatter = new DecimalFormat("#.00");

        formatter.setGroupingUsed(true);
        formatter.setGroupingSize(3);

	    System.out.println("Temperature Converter");
        System.out.println("---------------------");

        Scanner input = new Scanner(System.in);

        System.out.print("Base temperature units (C, F, K): ");

        String units = input.next();

        System.out.print("Value to convert: ");

        double value = input.nextDouble();

        System.out.println("---------------------\n");

        if (units.trim().equalsIgnoreCase("F")) {
            System.out.println(formatter.format(value) + "°F is " + formatter.format(convertFtoC(value)) + "°C.");
            System.out.println(formatter.format(value) + "°F is " + formatter.format(convertFtoK(value)) + "°K.");
        }

        if (units.trim().equalsIgnoreCase("C")) {
            System.out.println(formatter.format(value) + "°C is " + formatter.format(convertCtoF(value)) + "°F.");
            System.out.println(formatter.format(value) + "°C is " + formatter.format(convertCtoK(value)) + "°K.");
        }

        if (units.trim().equalsIgnoreCase("K")) {
            System.out.println(formatter.format(value) + "°K is " + formatter.format(convertKtoC(value)) + "°C.");
            System.out.println(formatter.format(value) + "°K is " + formatter.format(convertKtoF(value)) + "°F.");
        }
    }

    private static double convertFtoC(double temperature)
    {
        return ((temperature - 32) * 5) / 9;
    }

    private static double convertFtoK(double temperature)
    {
        return (((temperature - 32) * 5) / 9) + 273.15;
    }

    private static double convertCtoF(double temperature)
    {
        return ((temperature * 9) / 5) + 32;
    }

    private static double convertCtoK(double temperature)
    {
        return temperature + 273.15;
    }

    private static double convertKtoF(double temperature)
    {
        return (9 / 5) * (temperature - 273) + 32;
    }

    private static double convertKtoC(double temperature)
    {
        return temperature - 273.15;
    }
}
