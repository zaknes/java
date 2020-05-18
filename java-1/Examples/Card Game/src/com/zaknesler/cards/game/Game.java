package com.zaknesler.cards.game;

import com.zaknesler.cards.input.KeyInput;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{
    private Constants constants;

    private boolean running = false;
    private int fps = 0;

    private Thread thread;
    private KeyInput keys;

    public Game()
    {
        constants = new Constants();
        keys = new KeyInput();

        setPreferredSize(new Dimension(constants.WIDTH, constants.HEIGHT));
        addKeyListener(keys);
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        final double nanoseconds = 1E9 / 60.0;
        double delta = 0;

        int frames = 0;
        int updates = 0;

        requestFocus();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoseconds;

            lastTime = now;

            while (delta >= 1) {
                update();

                updates++;
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

                fps = frames;

                updates = 0;
                frames = 0;
            }
        }

        stop();
    }

    public synchronized void start()
    {
        running = true;

        thread = new Thread(this, "Cards");
        thread.start();
    }

    public synchronized void stop()
    {
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update()
    {
        keys.update();
    }

    private void render()
    {
        BufferStrategy bufferStrategy = getBufferStrategy();

        if (bufferStrategy == null) {
            createBufferStrategy(3);

            return;
        }

        Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.PLAIN, 20));

        graphics.drawString(String.valueOf(fps), 15, 25);

        graphics.dispose();
        bufferStrategy.show();
    }
}
