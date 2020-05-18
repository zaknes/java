package com.zaknesler;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        GraphicsSandbox graphics = new GraphicsSandbox();

        graphics.setPreferredSize(new Dimension(720, 480));

        frame.setTitle("Dark Side of the Moon");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(graphics);
        frame.pack();
        frame.setVisible(true);
    }
}
