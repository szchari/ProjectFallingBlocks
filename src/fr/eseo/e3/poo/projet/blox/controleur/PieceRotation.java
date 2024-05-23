package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.SwingUtilities;

public class PieceRotation
        extends MouseAdapter {
    private Puits puits;
    private VuePuits vuePuits;

    public PieceRotation(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (vuePuits.getPuits().getPieceActuelle() != null) {
            try {
                if (SwingUtilities.isRightMouseButton(event)) {
                    try {
                        puits.getPieceActuelle().tourner(true);
                    } catch (BloxException e) {
                        System.err.println("Erreur de rotation : " + e.getMessage());
                    }
                }
                else if (SwingUtilities.isLeftMouseButton(event)) {
                    try {
                        puits.getPieceActuelle().tourner(false);
                    } catch (BloxException e) {
                        System.err.println("Erreur de rotation : " + e.getMessage());
                    }
                }
            } catch (IllegalArgumentException e) {
                // ok
            }
            this.vuePuits.repaint();
        }
    }

    public void setPuits(Puits puits) {
        this.puits = puits;
    }
}
