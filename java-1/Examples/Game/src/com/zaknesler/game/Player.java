package com.zaknesler.game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Player
{
    private int x = 50;
    private int y = 50;

    public boolean isGoingUp = false;
    public boolean isGoingDown = false;
    public boolean isGoingLeft = false;
    public boolean isGoingRight = false;

    public boolean isGoingUp()
    {
        return isGoingUp;
    }

    public void setGoingUp(boolean goingUp)
    {
        isGoingUp = goingUp;
    }

    public boolean isGoingDown()
    {
        return isGoingDown;
    }

    public void setGoingDown(boolean goingDown)
    {
        isGoingDown = goingDown;
    }

    public boolean isGoingLeft()
    {
        return isGoingLeft;
    }

    public void setGoingLeft(boolean goingLeft)
    {
        isGoingLeft = goingLeft;
    }

    public boolean isGoingRight()
    {
        return isGoingRight;
    }

    public void setGoingRight(boolean goingRight)
    {
        isGoingRight = goingRight;
    }

    public void movePlayer(double changeX, double changeY)
    {
        int moveAmount = (int) ((720 * 480) * 0.00001);

        if (!isLegalMove()) {
            return;
        }

        x += (changeX * moveAmount);
        y += (changeY * moveAmount);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Rectangle getPlayer()
    {
        int size = (int) ((720 * 480) * 0.0001);

        return new Rectangle(getX(), getY(), size, size);

        // ImageIcon image = new ImageIcon("resources/character.png", "An image");

        // return new BufferedImage();
    }

    private boolean isLegalMove()
    {
        return true;
    }

    public Rectangle getPlayerBounds()
    {
        Rectangle bounds = getPlayer();
        bounds.add(5, 5);

        return bounds;
    }
}
