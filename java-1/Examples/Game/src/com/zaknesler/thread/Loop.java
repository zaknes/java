package com.zaknesler.thread;

import javax.swing.*;
import com.zaknesler.game.Game;
import com.zaknesler.listeners.KeyPressListener;

public class Loop extends Thread
{
    private Game game;
    private boolean running = true;

    public Loop(Game g)
    {
        game = g;
    }

    public void run()
    {
        while (true) {
            update();

            game.repaint();

            try {
                Thread.sleep(16);
            } catch (Exception e) {
                e.printStackTrace();

                toggleLoop();
            }
        }
    }

    public void toggleLoop()
    {
        running = !running;
    }

    private void update()
    {
        SwingUtilities.getRoot(game).addKeyListener(new KeyPressListener(game));

        calculateMovement();
    }

    public void calculateMovement()
    {
        // if (!game.getMap().contains(game.getPlayer().getPlayer())) {
        //     return;
        // }

        boolean w = game.getPlayer().isGoingUp();
        boolean a = game.getPlayer().isGoingLeft();
        boolean s = game.getPlayer().isGoingDown();
        boolean d = game.getPlayer().isGoingRight();

        // DIAGONAL MOVEMENT //
        if (w && d && !a) {game.getPlayer().movePlayer( 1/Math.sqrt(2),-1/Math.sqrt(2));}
        if (w && a && !d) {game.getPlayer().movePlayer(-1/Math.sqrt(2),-1/Math.sqrt(2));}
        if (s && d && !a) {game.getPlayer().movePlayer( 1/Math.sqrt(2), 1/Math.sqrt(2));}
        if (s && a && !d) {game.getPlayer().movePlayer(-1/Math.sqrt(2), 1/Math.sqrt(2));}

        // HOR AND VERT MOVEMENT //
        if ((w && !(a||d)) || (w && (a && d))) {game.getPlayer().movePlayer( 0,-1);}
        if ((a && !(w||s)) || (a && (w && s))) {game.getPlayer().movePlayer(-1, 0);}
        if ((s && !(a||d)) || (s && (a && d))) {game.getPlayer().movePlayer( 0, 1);}
        if ((d && !(w||s)) || (d && (w && s))) {game.getPlayer().movePlayer( 1, 0);}

        // NO MOVEMENT
        if (w && s) {game.getPlayer().movePlayer(0, 0);}
        if (a && d) {game.getPlayer().movePlayer(0, 0);}
    }
}
