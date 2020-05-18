package com.zaknesler.game;

import com.zaknesler.Main;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel
{
    public Player getPlayer()
    {
        return player;
    }

    public Rectangle getMap()
    {
        return map;
    }

    private Player player = new Player();

    private Rectangle map;

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        map = new Rectangle(10, 10, getWidth() - 20, getHeight() - 20);

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setBackground(Color.BLACK);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.draw(map);

        graphics.setColor(Color.RED);

        graphics.draw(player.getPlayer());
    }
}
