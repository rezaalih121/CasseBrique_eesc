package edu.fbansept.cassebrique;

import java.awt.*;
import java.util.ArrayList;

public class Balle extends Sprite{
    protected int x;
    protected int y;
    protected int vitesseHorizontal;
    protected int vitesseVertical;
    protected int diametre;
    protected int diametreReflet;
    protected int decalageReflet;

    protected Color couleur;

    protected Point[] listePoints = new Point[30];
    protected int indexFrame = 0;

    public Balle(int x, int y, int vitesseHorizontal, int vitesseVertical, int diametre, Color couleur) {
        super(x,y);
        this.vitesseHorizontal = vitesseHorizontal == 0 ? 1 : vitesseHorizontal;
        this.vitesseVertical = vitesseVertical == 0 ? 1 : vitesseVertical;
        this.setDiametre(diametre);
        this.couleur = couleur;
    }

    public Balle(int x, int y) {
        super(x,y);
        this.setDiametre(5);
        this.couleur = Color.WHITE;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVitesseHorizontal() {
        return vitesseHorizontal;
    }

    public void setVitesseHorizontal(int vitesseHorizontal) {
        this.vitesseHorizontal = vitesseHorizontal;
    }

    public int getVitesseVertical() {
        return vitesseVertical;
    }

    public void setVitesseVertical(int vitesseVertical) {
        this.vitesseVertical = vitesseVertical;
    }

    public int getDiametre() {
        return diametre;

    }

    public void setDiametre(int diametre) {
        this.diametre = diametre;
        this.diametreReflet = (int) (diametre * 0.3f);
        this.decalageReflet = (int) (diametre * 0.2f);
    }

    public int getDiametreReflet() {
        return diametreReflet;
    }


    public int getDecalageReflet() {
        return decalageReflet;
    }


    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void inverseVitesseVertical() {
        vitesseVertical *= -1;
    }

    public void inverseVitesseHorizontal() {
        vitesseHorizontal *= -1;
    }

    public void deplacer() {
        x += vitesseHorizontal;
        y += vitesseVertical;
    }

    public void dessiner(Graphics2D dessin) {


        dessin.fillOval(x, y, diametre, diametre);
        dessin.setColor(Color.WHITE);
        dessin.fillOval(x + decalageReflet, y + decalageReflet, diametreReflet, diametreReflet);
        //mydessinerPoints(dessin);
        dessinerPoints(dessin);
    }

    public void dessinerPoints(Graphics2D dessin) {
        indexFrame++;
        if (indexFrame % 10 == 0) {

            int indexPoint = (int) ((indexFrame / 10) % 10);

            if (indexFrame <= 100) {
                listePoints[indexPoint] = new Point(x, y);
            } else {
                listePoints[indexPoint].setX(x);
                listePoints[indexPoint].setY(y);
            }

        }

        for (Point point : listePoints) {
            if (point != null) {
                point.dessiner(dessin);
            }
        }
    }

    public void mydessinerPoints(Graphics2D dessin) {
        if (indexFrame == 10)
            indexFrame = 0;
        else
            indexFrame++;

        if ((indexFrame / 10) % 10 == 0 && vitesseHorizontal != 0 && vitesseVertical != 0) {
            listePoints[indexFrame] = new Point(x, y);
        }
        //TODO dessiner tous les points de cette balle
        for (int i = 0; i < 10; i++) {
            if (listePoints[i] != null) {
                listePoints[i].dessiner(dessin);

                int previousIndex = i - 1;
                if (i == 0) {
                    previousIndex = 9;
                }

                if (listePoints[previousIndex] != null) {
                    //dessin.setColor(Color.WHITE);
                    //dessin.drawLine(listepoints[previousIndex].getX(), listepoints[previousIndex].getY(), listepoints[i].getX(), listepoints[i].getY());
                }
            }
        }
    }

    public void testCollision(int largeurEcran, int hauteurEcran) {
        //mouvement balle
        if (x < 0 || x > largeurEcran - diametre) {
            inverseVitesseHorizontal();
        }

        if (y < 0 || y > hauteurEcran - diametre) {
            inverseVitesseVertical();
        }
    }


}
