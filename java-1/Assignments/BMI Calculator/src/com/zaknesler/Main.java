package com.zaknesler;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
	    System.out.println("BMI Calculator");

	    // Get the weight and height.
        String weight = JOptionPane.showInputDialog(null, "Please enter your weight (in pounds).");
        String height = JOptionPane.showInputDialog(null, "Please enter your height (in inches).");

        // Ensure that the weight is a number.
        if (!weight.matches("\\d+")) {
            System.out.println("Invalid weight.");

            System.exit(0);
        }

        // Ensure that the height is a number.
        if (!height.matches("\\d+")) {
            System.out.println("Invalid height.");

            System.exit(0);
        }

        // Convert the string inputs to doubles and pass them to the `calculateBMI` method.
        double bmi = calculateBMI(Double.parseDouble(weight), Double.parseDouble(height));

        System.out.println("--------------");
        System.out.println("Underweight   = below 18.5");
        System.out.println("Normal weight = 18.5 - 24.9");
        System.out.println("Overweight    = 25.0 - 29.9");
        System.out.println("Obese         = above 30");
        System.out.println("--------------");

        System.out.println("Your BMI is " + bmi + " (" + getBmiRange(bmi) + ")");
    }

    private static double calculateBMI(double weight, double height)
    {
        // Perform the calculation on the given weight and height.
        return Math.round((weight / Math.pow(height, 2)) * 703);
    }

    private static String getBmiRange(double bmi)
    {
        // If the bmi is below 18.5.
        if (bmi < 18.5) {
            return "underweight";
        }

        // If the bmi if greater than or equal to 18.5 and less than or equal to 24.9.
        if (bmi >= 18.5 && bmi <= 24.9) {
            return "normal weight";
        }

        // If the bmi if greater than or equal to 25.0 and less than or equal to 29.9.
        if (bmi >= 25.0 && bmi <= 29.9) {
            return "overweight";
        }

        // If the bmi is greater than or equal to 30.0.
        if (bmi >= 30.0) {
            return "obese";
        }

        return null;
    }
}
