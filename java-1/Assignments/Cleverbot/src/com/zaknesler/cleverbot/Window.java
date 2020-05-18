package com.zaknesler.cleverbot;

import okhttp3.*;
import java.awt.*;
import javax.swing.*;
import org.json.JSONObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends Canvas implements Runnable
{
    private final String API_KEY = "[Redacted]";

    private String cs_id = "";

    private JPanel panel;
    private JTextField input;
    private JEditorPane textbox;

    public Window()
    {
        input.setBorder(BorderFactory.createCompoundBorder(input.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        input.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (input.getText().trim().length() == 0) {
                    return;
                }

                transaction(input.getText().trim());
            }
        });
    }

    public void run()
    {
        JFrame frame = new JFrame("Cleverbot");

        panel.setPreferredSize(new Dimension(600, 400));

        frame.add(panel);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void transaction(String message)
    {
        addLine("YOU: " + message);

        JSONObject response = request(message, cs_id);

        this.cs_id = response.getString("cs");
        this.input.setText("");

        addLine("BOT: " + response.getString("output"));
    }

    private void addLine(String line)
    {
        textbox.setText(textbox.getText().concat("\n" + line));
    }

    private JSONObject request(String text, String cs)
    {
        try {
            OkHttpClient client = new OkHttpClient.Builder().followRedirects(true).followSslRedirects(true).build();

            Request request = new Request.Builder().url("http://www.cleverbot.com/getreply?key=" + API_KEY + "&input=" + text + (cs.isEmpty() ? "" : "&cs=" + cs)).get().addHeader("content-type", "application/json; charset=UTF-8").build();

            Response response = client.newCall(request).execute();

            Thread.sleep(1000);

            return new JSONObject(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }

}
