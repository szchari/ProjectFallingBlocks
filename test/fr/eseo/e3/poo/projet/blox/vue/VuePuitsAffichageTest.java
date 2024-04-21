package fr.eseo.e3.poo.projet.blox.vue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.Puits;

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
}

