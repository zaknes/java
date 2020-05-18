package com.zaknesler;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
	    System.out.println("Quadratic Formula Calculator");

        // Get three values for formula.
        String input = JOptionPane.showInputDialog(null, "Enter values a, b, and c (separated by commas).");

        // Remove spaces and split into array using commas.
        String[] numbers = input.replace(" ", "").split(",");

        // Instantiate a new array for the converted values.
        double[] values = new double[numbers.length];

        // Loop through all of the string values and convert them to doubles.
        for (int i = 0; i < numbers.length; i++) {
            values[i] = Double.parseDouble(numbers[i]);
        }

        // Call the calculate method and save it to a variable.
	    double[] total = calculate(values[0], values[1], values[2]);

        // Ensure that answer does not contain an imaginary number.
        if (Double.isNaN(total[0]) || Double.isNaN(total[1])) {
            System.out.println("\nError: Answer contains imaginary numbers.");

            System.exit(0);
        }

        // Output results.
        System.out.println("----------------------------");
        System.out.println("a = " + values[0] + ", b = " + values[1] + ", c = " + values[2]);
        System.out.println("----------------------------");
	    System.out.println("x1 = " + total[0]);
        System.out.println("x2 = " + total[1]);
    }

    private static double[] calculate(double a, double b, double c)
    {
        // Instantiate a new double with two empty values.
        double[] results = new double[2];

        // Save first operation to array, using addition.
        results[0] = ((-b) + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);

        // Save second operation to array, using subtraction.
        results[1] = ((-b) - Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);

        // Return the results.
        return results;
    }
}
