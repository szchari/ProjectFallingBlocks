package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuitsAffichageTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class PieceDeplacementTest {
    private void testPieceDeplacement() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Déplacement Piece");
            Puits puits = new Puits();
            int taille = 20;
            VuePuits vuePuits = new VuePuits(puits, taille);

            UsineDePiece.setMode(UsineDePiece.CYCLIC);
            Tetromino tetromino = UsineDePiece.genererTetromino();
            Tetromino tetromino2 = UsineDePiece.genererTetromino();
            puits.setPieceSuivante(tetromino);
            puits.setPieceSuivante(tetromino2);

            for(int i = 0; i < 8; i++) {
                puits.getPieceActuelle().deplacerDe(0, 1);
            }

            frame.add(vuePuits);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            // Vérification de la taille préférée
            Assertions.assertEquals(vuePuits.getPreferredSize(), frame.getContentPane().getSize());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PieceDeplacementTest().runTests());
    }

    public void runTests() {
        testPieceDeplacement();
    }
}
