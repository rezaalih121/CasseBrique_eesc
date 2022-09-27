package edu.fbansept.cassebrique;

import java.awt.*;

public class Point extends Sprite{
    public Point(int x, int y) {
        super(x, y);
    }
    public void dessiner(Graphics2D dessin){
        dessin.fillOval(x, y, 5, 5);
        dessin.setColor(Color.WHITE);
    }
}
