package com.zaknesler;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

public class GraphicsSandbox extends JComponent
{
    public void paintComponent(Graphics g)
    {
        Graphics2D graphics = (Graphics2D) g;

        graphics.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        // I just created a method for each flag.

        // franceFlag(graphics);
        swissFlag(graphics);
        // dominicanRepublicFlag(graphics);
        // bangladeshFlag(graphics);
        // texasFlag(graphics);
    }

    private static void franceFlag(Graphics2D graphics)
    {
        // Window size: 720x480

        Rectangle blue = new Rectangle(0, 0, 240, 480);
        graphics.setColor(Color.BLUE);
        graphics.draw(blue);
        graphics.fill(blue);

        Rectangle white = new Rectangle(240, 0, 240, 480);
        graphics.setColor(Color.WHITE);
        graphics.draw(white);
        graphics.fill(white);

        Rectangle red = new Rectangle(480, 0, 240, 480);
        graphics.setColor(Color.RED);
        graphics.draw(red);
        graphics.fill(red);
    }

    private static void swissFlag(Graphics2D graphics)
    {
        // Window size: 440x440

        Rectangle square = new Rectangle(0, 0, 440, 440);
        graphics.setColor(Color.RED);
        graphics.draw(square);
        graphics.fill(square);

        Rectangle vertical = new Rectangle(172, 60, 96, 320);
        graphics.setColor(Color.WHITE);
        graphics.draw(vertical);
        graphics.fill(vertical);

        Rectangle horizontal = new Rectangle(60, 172, 320, 96);
        graphics.setColor(Color.WHITE);
        graphics.draw(horizontal);
        graphics.fill(horizontal);
    }

    private static void dominicanRepublicFlag(Graphics2D graphics)
    {
        // Window size: 720x480

        Rectangle background = new Rectangle(0, 0, 720, 480);
        graphics.setColor(Color.WHITE);
        graphics.draw(background);
        graphics.fill(background);

        Rectangle topLeft = new Rectangle(0, 0, 324, 204);
        graphics.setColor(Color.BLUE);
        graphics.draw(topLeft);
        graphics.fill(topLeft);

        Rectangle topRight = new Rectangle(396, 0, 324, 204);
        graphics.setColor(Color.RED);
        graphics.draw(topRight);
        graphics.fill(topRight);

        Rectangle bottomLeft = new Rectangle(0, 276, 324, 204);
        graphics.setColor(Color.RED);
        graphics.draw(bottomLeft);
        graphics.fill(bottomLeft);

        Rectangle bottomRight = new Rectangle(396, 276, 324, 204);
        graphics.setColor(Color.BLUE);
        graphics.draw(bottomRight);
        graphics.fill(bottomRight);
    }

    private static void bangladeshFlag(Graphics2D graphics)
    {
        // Window size: 720x480

        Rectangle background = new Rectangle(0, 0, 720, 480);
        graphics.setColor(Color.decode("#006a4e"));
        graphics.draw(background);
        graphics.fill(background);

        Ellipse2D.Double circle = new Ellipse2D.Double(180, 96, 288, 288);
        graphics.setColor(Color.decode("#f42a41"));
        graphics.draw(circle);
        graphics.fill(circle);
    }

    private static void texasFlag(Graphics2D graphics)
    {
        // Window size: 720x480

        Rectangle leftSide = new Rectangle(0, 0, 240, 480);
        graphics.setColor(Color.decode("#002868"));
        graphics.draw(leftSide);
        graphics.fill(leftSide);

        graphics.setColor(Color.WHITE);
        int[] x = {113, 134, 199, 146, 166, 114,  61,  81,  28,  93, 113};
        int[] y = {155, 217, 217, 255, 317, 279, 317, 255, 217, 217, 155};
        graphics.fillPolygon(x, y, x.length);

        Rectangle topRight = new Rectangle(240, 0, 480, 240);
        graphics.setColor(Color.WHITE);
        graphics.draw(topRight);
        graphics.fill(topRight);

        Rectangle bottomRight = new Rectangle(240, 240, 480, 240);
        graphics.setColor(Color.decode("#bf0a30"));
        graphics.draw(bottomRight);
        graphics.fill(bottomRight);
    }
}
