package com.zaknesler;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main
{
    private static final double growthRate = 0.0111;
    private static final double currentPopulation = 7.488;
    private static final double currentYear = 2017;

    public static void main(String[] args)
    {
	    System.out.println("World Population Growth");
        System.out.println("-----------------------");

        System.out.println("The world population in 2019 will be " + calculate(2) + " billion.");
        System.out.println("The world population in 2020 will be " + calculate(3) + " billion.");
        System.out.println("The world population in 2022 will be " + calculate(5) + " billion.");

        System.out.println("-----------------------\n");

        Scanner input = new Scanner(System.in);

        System.out.print("Enter a year to calculate the population of: ");

        int year = input.nextInt();

        System.out.println("The population in " + year + " will be " + calculate(year - currentYear) + " billion.");
    }

    private static String calculate(double factor)
    {
        DecimalFormat format = new DecimalFormat("#.##");

        format.setGroupingUsed(true);
        format.setGroupingSize(3);

        return format.format(currentPopulation * Math.exp(growthRate * factor));
    }
}
