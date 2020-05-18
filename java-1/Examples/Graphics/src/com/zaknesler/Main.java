package com.zaknesler;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame(); // Creates new JFrame.
        GraphicsSandbox graphics = new GraphicsSandbox();

        graphics.setPreferredSize(new Dimension(720, 480)); // Sets the size of the window in the format x,y (pixels).

        frame.setTitle("Flags"); // Sets the title of the window.
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Makes sure the application exits when the window is closed.
        frame.add(graphics);
        frame.pack();
        frame.setVisible(true); // Makes the window visible to the user.
    }
}
