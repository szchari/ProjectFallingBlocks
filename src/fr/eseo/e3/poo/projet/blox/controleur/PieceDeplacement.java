package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PieceDeplacement
        implements MouseMotionListener {
    private Puits puits;
    private VuePuits vuePuits;
    private int derniereColonne = -1;
    private boolean debutDeplacement = true;

    public PieceDeplacement(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        if (vuePuits.getPuits().getPieceActuelle() != null) {
            int tailleCellule = vuePuits.getTaille();
            int xSouris = event.getX();
            int colonneSouris = xSouris / tailleCellule;

            if(this.debutDeplacement) {
                this.debutDeplacement = false; // Marque la fin du mouvement initial de la souris
            }else if(this.derniereColonne != colonneSouris) {
                try {
                    int dX = colonneSouris > derniereColonne ? 1 : -1;
                    System.out.println("ICI");
                    puits.getPieceActuelle().deplacerDe(dX, 0);
                } catch (IllegalArgumentException ignored) {
                    System.out.println(ignored);
                    // ignorer les déplacements invalides
                }
            }

            this.derniereColonne = colonneSouris;
            this.vuePuits.repaint();

            System.out.println(this.puits.getPieceActuelle().toString());
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        // non used pour l'instant
    }

    public void setPuits(Puits puits) {
        this.puits = puits;
    }
}
