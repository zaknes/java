package com.zaknesler;

import com.zaknesler.game.Game;
import com.zaknesler.thread.Loop;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static int width = 720;
    public static int height = 480;

    private static Game game = new Game();
    private static JFrame frame = new JFrame("Game");

    public static void main(String[] args)
    {
        game.setPreferredSize(new Dimension(width, height));

        frame.add(game);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.pack();
        frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    frame.requestFocus();

        startThread();
    }

    private static void startThread()
    {
        Loop loop = new Loop(game);

        loop.start();
    }
}
