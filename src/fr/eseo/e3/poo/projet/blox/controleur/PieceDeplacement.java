package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

public class PieceDeplacement
        extends MouseAdapter {
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

            if (this.debutDeplacement) {
                this.debutDeplacement = false;
            } else if (this.derniereColonne != colonneSouris) {
                try {
                    int dX = colonneSouris > derniereColonne ? 1 : -1;
                    puits.getPieceActuelle().deplacerDe(dX, 0);
                } catch (IllegalArgumentException ignored) {
                    // ignorer les déplacements invalides
                }
            }

            this.derniereColonne = colonneSouris;
            this.vuePuits.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        this.derniereColonne = -1; // Réinitialiser la dernière colonne
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        if (vuePuits.getPuits().getPieceActuelle() != null) {
            int notches = event.getWheelRotation();
            if (notches > 0) {
                puits.getPieceActuelle().deplacerDe(0, 1);
                vuePuits.repaint();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        // non utilisé pour l'instant
    }

    public void setPuits(Puits puits) {
        this.puits = puits;
    }
}