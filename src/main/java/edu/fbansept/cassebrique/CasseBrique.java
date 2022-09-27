package edu.fbansept.cassebrique;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CasseBrique extends Canvas {
    protected int largeurEcran = 600;
    protected int hauteurEcran = 600;

    public CasseBrique() throws InterruptedException {


        JFrame fenetre = new JFrame("Casse brique");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();
        //On définie la hauteur / largeur de l'écran
        panneau.setPreferredSize(new Dimension(largeurEcran, hauteurEcran));
        setBounds(0, 0, largeurEcran, hauteurEcran);
        //On ajoute cette classe (qui hérite de Canvas) comme composant du panneau principal
        panneau.add(this);

        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.requestFocus();

        //On indique que le raffraichissement de l'ecran doit être fait manuellement.
        createBufferStrategy(2);
        setIgnoreRepaint(true);
        this.setFocusable(false);

        demarrer();
    }

    public void demarrer() throws InterruptedException {

        long indexFrame = 0;

        ArrayList<Balle> listeBalles = new ArrayList<Balle>();




        for (int i = 0; i <= 50; i++) {
            listeBalles.add(new Balle((int) (Math.random() * largeurEcran), (int) (Math.random() * hauteurEcran), (int) (Math.random() * 10), -(int) (Math.random() * 10), (int) (Math.random() * 30), new Color((float) Math.random(), (float) Math.random(), (float) Math.random())));
        }


        while (true) {
            indexFrame++;
            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            ArrayList<Sprite> listeDeSprite = new ArrayList<>();
            listeDeSprite.add(new Balle(1,2,1,2,1,Color.yellow));
            listeDeSprite.add(new Point(1,2));


            //reset dessin
            dessin.fillRect(0, 0, largeurEcran, hauteurEcran);

            //dessin balle
            for (Balle balle : listeBalles) {
                balle.deplacer();
                dessin.setColor(balle.getCouleur());
                balle.dessiner(dessin);
                balle.testCollision(largeurEcran, hauteurEcran);
            }

            dessin.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new CasseBrique();
    }
}