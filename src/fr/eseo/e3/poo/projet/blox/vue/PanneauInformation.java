package fr.eseo.e3.poo.projet.blox.vue;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public class PanneauInformation extends JPanel implements PropertyChangeListener {
    private static final int TAILLE_PREFEREE = 70;
    private VuePiece vuePiece;

    public PanneauInformation(Puits puits) {
        this.setPreferredSize(new Dimension(TAILLE_PREFEREE, TAILLE_PREFEREE));
        puits.addPropertyChangeListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (vuePiece != null) {
            Graphics2D g2D = (Graphics2D) g.create(); // Créer un objet Graphics2D à partir de Graphics
            vuePiece.afficherPiece(g2D);
            g2D.dispose(); // Libérer les ressources de l'objet Graphics2D
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Puits.MODIFICATION_PIECE_SUIVANTE)) {
            Piece nouvellePiece = (Piece) evt.getNewValue();
            vuePiece = new VuePiece(nouvellePiece, 10); // Taille de la pièce suivante
            repaint();
        }
    }
}
