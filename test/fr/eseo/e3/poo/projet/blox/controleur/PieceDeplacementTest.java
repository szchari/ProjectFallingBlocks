package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import org.junit.jupiter.api.Assertions;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class PieceDeplacementTest {
    private void testPieceDeplacement() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Déplacement Piece");
            Puits puits = new Puits();
            int taille = 20;
            VuePuits vuePuits = new VuePuits(puits, taille);

            UsineDePiece.setMode(UsineDePiece.ALEATOIRE_PIECE);
            Tetromino tetromino = UsineDePiece.genererTetromino();
            Tetromino tetromino2 = UsineDePiece.genererTetromino();
            puits.setPieceSuivante(tetromino);
            puits.setPieceSuivante(tetromino2);

            for (int i = 0; i < 8; i++) {
                try {
                    puits.getPieceActuelle().deplacerDe(0, 1);
                } catch (BloxException e) {
                    System.err.println("Erreur de déplacement : " + e.getMessage());
                }
            }

            frame.add(vuePuits);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
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
