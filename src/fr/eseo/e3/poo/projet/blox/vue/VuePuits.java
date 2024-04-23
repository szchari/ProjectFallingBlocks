package fr.eseo.e3.poo.projet.blox.vue;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public class VuePuits extends JPanel implements PropertyChangeListener {
    public static final int TAILLE_PAR_DEFAUT = 20;
    public int taille;
    private Puits puits;
    private VuePiece vuePiece;

    public VuePuits(Puits puits) {
        setPuits(puits);
        setTaille(TAILLE_PAR_DEFAUT);

    }
    public VuePuits(Puits puits, int taille) {
        setPuits(puits);
        setTaille(taille);
    }

    public Puits getPuits() {
        return this.puits;
    }
    public int getTaille() {
        return this.taille;
    }
    /* public void setPuits(Puits puits) {
        this.puits = puits;
        updatePreferredSize();
        repaint();
    }*/

    public void setPuits(Puits nouveauPuits) {
        if (this.puits != null) {
            this.puits.removePropertyChangeListener(this);
        }

        this.puits = nouveauPuits;
        this.puits.addPropertyChangeListener(this);

        updatePreferredSize();
    }
    public void setTaille(int taille) {
        this.taille = taille;
        updatePreferredSize();
    }
    private void updatePreferredSize() {
        int largeurPanel = this.puits.getLargeur() * taille;
        int profondeurPanel = this.puits.getProfondeur() * taille;
        setPreferredSize(new Dimension(largeurPanel, profondeurPanel));
    }
    public VuePiece getVuePiece() {
        return this.vuePiece;
    }
    private void setVuePiece(VuePiece vuepiece) {
        this.vuePiece = vuepiece;
        repaint();
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Puits.MODIFICATION_PIECE_ACTUELLE)) {
            VuePiece nouvelleVuePiece = new VuePiece((Piece) evt.getNewValue(), this.taille);
            setVuePiece(nouvelleVuePiece);
        }
    }
}
