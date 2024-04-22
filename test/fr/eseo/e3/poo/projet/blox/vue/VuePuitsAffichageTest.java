package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.UsineDePiece;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VuePuitsAffichageTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VuePuitsAffichageTest().runTests());
    }

    public void runTests() {
        testConstructeurPuits();
        testConstructeurPuitsTaille();
    }

    private void testConstructeurPuits() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Puits");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Puits puits = new Puits();
            VuePuits vuePuits = new VuePuits(puits);
            frame.add(vuePuits);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Vérification de la taille préférée
            Assertions.assertEquals(vuePuits.getPreferredSize(), frame.getContentPane().getSize());
        });
    }

    private void testConstructeurPuitsTaille() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Puits et taille");
            Puits puits = new Puits();
            int taille = 20;
            VuePuits vuePuits = new VuePuits(puits, taille);
            frame.add(vuePuits);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);

            // Vérification de la taille préférée
            Assertions.assertEquals(vuePuits.getPreferredSize(), frame.getContentPane().getSize());
        });
    }

    @Test
    public void testAffichageVuePuits() {
        SwingUtilities.invokeLater(() -> {
            Puits puits = new Puits();

            VuePuits vuePuits = new VuePuits();
            vuePuits.setPuits(puits); // la ligne permet d'associer le puits à vuepuits

            Piece piece = UsineDePiece.genererTetromino(); // genere une piece

            VuePiece vuePiece = new VuePiece(piece, VuePuits.TAILLE_PAR_DEFAUT);
            vuePuits.setVuePiece(vuePiece); // la ligne permet d'associer la piece à vuepuits

            JFrame frame = new JFrame("Test Affichage VuePuits");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(vuePuits);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

