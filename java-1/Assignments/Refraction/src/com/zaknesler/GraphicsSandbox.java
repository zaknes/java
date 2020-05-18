package com.zaknesler;

import java.awt.*;
import javax.swing.JComponent;

public class GraphicsSandbox extends JComponent
{
    public void paintComponent(Graphics g)
    {
        Graphics2D graphics = (Graphics2D) g;

        graphics.setRenderingHints(
            new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        );

        Rectangle background = new Rectangle(0, 0, 720, 480);
        graphics.setColor(Color.BLACK);
        graphics.draw(background);
        graphics.fill(background);

        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(3));
        graphics.drawLine(0, 280, 324, 240);

        graphics.setPaint(new GradientPaint(324, 240, Color.WHITE, 396, 240, Color.BLACK, false));
        int[] middleX = {324, 393, 398};
        int[] middleY = {240, 224, 254};
        graphics.fillPolygon(middleX, middleY, middleX.length);

        graphics.setColor(Color.decode("#f7221c"));
        int[] redX = {393, 393, 720, 720};
        int[] redY = {230, 224, 249, 260};
        graphics.fillPolygon(redX, redY, redX.length);

        graphics.setColor(Color.decode("#ffac1e"));
        int[] orangeX = {394, 394, 720, 720};
        int[] orangeY = {235, 229, 259, 270};
        graphics.fillPolygon(orangeX, orangeY, orangeX.length);

        graphics.setColor(Color.decode("#fffc1e"));
        int[] yellowX = {395, 395, 720, 720};
        int[] yellowY = {240, 234, 269, 280};
        graphics.fillPolygon(yellowX, yellowY, yellowX.length);

        graphics.setColor(Color.decode("#3ec721"));
        int[] greenX = {396, 396, 720, 720};
        int[] greenY = {245, 239, 279, 290};
        graphics.fillPolygon(greenX, greenY, greenX.length);

        graphics.setColor(Color.decode("#1f95bd"));
        int[] blueX = {397, 397, 720, 720};
        int[] blueY = {250, 244, 289, 300};
        graphics.fillPolygon(blueX, blueY, blueX.length);

        graphics.setColor(Color.decode("#5e38b1"));
        int[] purpleX = {398, 398, 720, 720};
        int[] purpleY = {254, 249, 299, 309};
        graphics.fillPolygon(purpleX, purpleY, purpleX.length);

        graphics.setColor(Color.WHITE);
        int[] x = {288, 360, 432};
        int[] y = {312, 168, 312};
        graphics.setStroke(new BasicStroke(8));
        graphics.drawPolygon(x, y, x.length);
    }
}
