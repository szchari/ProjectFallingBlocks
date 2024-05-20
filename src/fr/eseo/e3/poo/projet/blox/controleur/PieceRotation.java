package fr.eseo.e3.poo.projet.blox.controleur;

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
                    puits.getPieceActuelle().tourner(true);
                }
                else if (SwingUtilities.isLeftMouseButton(event)) {
                    puits.getPieceActuelle().tourner(false);
                }
            } catch (IllegalArgumentException ignored) {
                // ignored
            }
            this.vuePuits.repaint();
        }
    }

    public void setPuits(Puits puits) {
        this.puits = puits;
    }
}
