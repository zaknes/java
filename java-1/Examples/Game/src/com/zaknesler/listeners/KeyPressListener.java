package com.zaknesler.listeners;

import com.zaknesler.game.Game;

import java.awt.event.*;

public class KeyPressListener implements KeyListener
{
    private Game game;

    public KeyPressListener(Game g)
    {
        this.game = g;
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            game.getPlayer().setGoingUp(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            game.getPlayer().setGoingDown(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            game.getPlayer().setGoingLeft(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            game.getPlayer().setGoingRight(true);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            game.getPlayer().setGoingUp(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            game.getPlayer().setGoingDown(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            game.getPlayer().setGoingLeft(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            game.getPlayer().setGoingRight(false);
        }
    }
}
