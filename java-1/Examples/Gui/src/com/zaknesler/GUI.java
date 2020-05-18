package com.zaknesler;

import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class GUI
{
    private JPanel panel;
    private JTextField fahrenheitTextField;
    private JTextField celsiusTextField;
    private JTextField kelvinTextField;
    private JLabel errorMessage;
    private JButton clearButton;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Temperature Conversion");

        frame.setContentPane(new GUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private GUI()
    {
        DecimalFormat formatter = new DecimalFormat("0.00");

        fahrenheitTextField.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    errorMessage.setText("");

                    fahrenheitTextField.setText(formatter.format(Double.parseDouble(fahrenheitTextField.getText())));
                    celsiusTextField.setText(formatter.format(convertFtoC(Double.parseDouble(fahrenheitTextField.getText()))));
                    kelvinTextField.setText(formatter.format(convertFtoK(Double.parseDouble(fahrenheitTextField.getText()))));
                } catch (Exception exception) {
                    errorMessage.setText("Invalid input.");
                }
            }
        });

        celsiusTextField.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    errorMessage.setText("");

                    fahrenheitTextField.setText(formatter.format(convertCtoF(Double.parseDouble(celsiusTextField.getText()))));
                    celsiusTextField.setText(formatter.format(Double.parseDouble(celsiusTextField.getText())));
                    kelvinTextField.setText(formatter.format(convertCtoK(Double.parseDouble(celsiusTextField.getText()))));
                } catch (Exception exception) {
                    errorMessage.setText("Invalid input.");
                }
            }
        });

        kelvinTextField.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    errorMessage.setText("");

                    fahrenheitTextField.setText(formatter.format(convertKtoF(Double.parseDouble(kelvinTextField.getText()))));
                    celsiusTextField.setText(formatter.format(convertKtoC(Double.parseDouble(kelvinTextField.getText()))));
                    kelvinTextField.setText(formatter.format(Double.parseDouble(kelvinTextField.getText())));
                } catch (Exception exception) {
                    errorMessage.setText("Invalid input.");
                }
            }
        });

        clearButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                fahrenheitTextField.setText("");
                celsiusTextField.setText("");
                kelvinTextField.setText("");
            }
        });
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
        return (1.8 * (temperature - 273.15)) + 32;
    }

    private static double convertKtoC(double temperature)
    {
        return temperature - 273.15;
    }
}
