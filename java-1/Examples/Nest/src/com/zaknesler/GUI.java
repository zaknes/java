package com.zaknesler;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI
{
    public JPanel panel;
    private JLabel valueName;
    private JLabel valueStatus;
    private JLabel valueBattery;
    private JLabel valueSmoke;
    private JLabel valueMonoxide;
    private JButton refreshButton;

    public GUI() throws IOException
    {
        update();

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try {
                    update();
                } catch (Exception e) {
                    System.out.println("Something went wrong.");
                }
            }
        });
}

    private void update() throws IOException
    {
        JSONObject data = Main.getData();

        valueName.setText(data.getString("name"));
        valueStatus.setText(data.getBoolean("is_online") ? "Online" : "Offline");
        valueBattery.setText(data.getString("battery_health").toUpperCase());
        valueSmoke.setText(data.getString("smoke_alarm_state").toUpperCase());
        valueMonoxide.setText(data.getString("co_alarm_state").toUpperCase());
    }
}
