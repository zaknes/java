package com.zaknesler;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        // Say good morning.
        System.out.println("Good morning,");

        // Advance to the next line and output the user's full name.
        System.out.println(args[0] + " " + args[1] + ".");

        // Prompt the user for a string containing comma-separated numbers.
        String input = JOptionPane.showInputDialog(null, "Enter five numbers, separated by commas.");

        // Split the string by commas into an array of numbers (in string form).
        String[] numbers = input.replace(" ", "").split(",");

        // Initialize a new integers array.
        int[] integers = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            integers[i] = Integer.parseInt(numbers[i]);
        }

        // Make sure input is sensible.
        if (integers[4] == 0) {
            System.out.println("CANNOT DIVIDE BY ZERO!");

            System.exit(0);
        }

        System.out.println("\n(((" + integers[0] + " + " + integers[1] + ") * " + integers[2] + ") - " + integers[3] + ") / " + integers[4]);

        System.out.println("= " + (((integers[0] + integers[1]) * integers[2]) - integers[3]) + " / " + integers[4]);

        // a plus b, multiply by c, subtract d, divide by e.
        System.out.println("= " + (((integers[0] + integers[1]) * integers[2]) - integers[3]) / integers[4]);

        // Output goodbye.
        System.out.println("\nGoodbye!");
    }
}
