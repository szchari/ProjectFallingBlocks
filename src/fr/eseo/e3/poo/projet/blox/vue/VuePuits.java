package fr.eseo.e3.poo.projet.blox.vue;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import fr.eseo.e3.poo.projet.blox.modele.Puits;

public class VuePuits extends JPanel {
    public static final int TAILLE_PAR_DEFAUT = 20;
    public int taille;
    private Puits puits;
    private VuePiece vuePiece;

    public VuePuits() {
        this.vuePiece = null;
    }
    public VuePuits(Puits puits) {
        this.taille = TAILLE_PAR_DEFAUT;
        this.puits = puits;
        updatePreferredSize();
    }
    public VuePuits(Puits puits, int taille) {
        this.taille = taille;
        this.puits = puits;
        updatePreferredSize();
    }

    public Puits getPuits() {
        return this.puits;
    }
    public int getTaille() {
        return this.taille;
    }
    public void setPuits(Puits puits) {
        this.puits = puits;
        updatePreferredSize();
        repaint();
    }
    public void setTaille(int taille) {
        this.taille = taille;
        updatePreferredSize();
        repaint();
    }
    private void updatePreferredSize() {
        int largeurPanel = puits.getLargeur() * taille;
        int profondeurPanel = puits.getProfondeur() * taille;
        setPreferredSize(new Dimension(largeurPanel, profondeurPanel));
    }
    public VuePiece getVuePiece() {
        return this.vuePiece;
    }
    public void setVuePiece(VuePiece vuepiece) {
        this.vuePiece = vuepiece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /* appel vers super pour remplir le fond du JPanel */

        /*Le paramètre g est copie en utilisant la méthode copie()
         * puis converti en instance de Graphics2D grâce à
         * un transtypage (cast) explicite.
         */
        Graphics2D g2D = (Graphics2D) g.create();
        /* Nous utiliserons l'instance de Graphics2D*/
        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, getWidth(), getHeight());

        g2D.setColor(Color.LIGHT_GRAY);
        int carreauSize = taille;
        int largeurPuits = puits.getLargeur();
        int profondeurPuits = puits.getProfondeur();

        for (int x = 0; x < largeurPuits; x++) {
            for (int y = 0; y < profondeurPuits; y++) {
                int xPosition = x * carreauSize;
                int yPosition = y * carreauSize;
                g2D.drawRect(xPosition, yPosition, carreauSize, carreauSize);
            }
        }

        /* Dessin de la piece seulement si associé : */
        if (vuePiece != null) {
            vuePiece.afficherPiece(g2D);
        }
        /*Puis nous liberons la memoire*/
        g2D.dispose();
    }
}
