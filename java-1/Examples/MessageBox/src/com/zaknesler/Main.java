package com.zaknesler;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        new Main().advanced();
    }

    private void advanced()
    {
        Object[] options = {
            "plus (+)",
            "minus (-)",
            "times (x)",
            "divided by (/)",
        };

        double total = 0;

        String input1 = JOptionPane.showInputDialog(null, "First number.");

        int operation = JOptionPane.showOptionDialog(null, "Operation?", "The last step!!!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

        String input2 = JOptionPane.showInputDialog(null, "Second number.");

        if (!this.isNumeric(input1) || !this.isNumeric(input2)) {
            JOptionPane.showMessageDialog(null, "Invalid input.");

            System.exit(0);
        }

        int first = Integer.parseInt(input1);
        int second = Integer.parseInt(input2);

        if (operation == 0) {
            total = (first + second);
        }

        if (operation == 1) {
            total = (first - second);
        }

        if (operation == 2) {
            total = (first * second);
        }

        if (operation == 3) {
            total = (first / second);
        }

        JOptionPane.showMessageDialog(null, "Total: " + total);

        System.exit(0);
    }

    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }
}