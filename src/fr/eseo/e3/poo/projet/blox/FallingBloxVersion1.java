/*
    ---------------------------------------------------------
    Project Falling Blocks de Zacharia Vandomme
    ---------------------------------------------------------
    Aidé par moment par : Clément Raffray
    Entraide par moment avec : Hippolyte Jospin, Théo Chupin
    ---------------------------------------------------------
    Utilisation par moment de : ChatGPT, Forum java, java 11
    ---------------------------------------------------------
*/
package fr.eseo.e3.poo.projet.blox;

import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.Tas;
import fr.eseo.e3.poo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;
import fr.eseo.e3.poo.projet.blox.vue.PanneauInformation;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class FallingBloxVersion1 {
    private FallingBloxVersion1() {
        throw new IllegalStateException("Utility class");
    }
    public static void main(String[] args) {
        int nbElements = 0;
        int nbLignes = 0;
        int taille = 20;

        if (args.length >= 1) {
            nbElements = Integer.parseInt(args[0]);
            if (nbElements < 0) {
                System.err.println("Le nombre d'éléments ne peut pas être négatif.");
                return; // stop
            }
        }

        Puits puits = new Puits();
        VuePuits vuePuits = new VuePuits(puits, taille);

        // 1 seul arg
        if (args.length == 1) {
            Tas tas = new Tas(puits, nbElements);
        }

        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_COMPLET);
        Tetromino tetromino = UsineDePiece.genererTetromino();
        Tetromino tetromino2 = UsineDePiece.genererTetromino();

        PanneauInformation panneauInformation = new PanneauInformation(puits);
        Gravite gravite = new Gravite(vuePuits);


        puits.setPieceSuivante(tetromino);
        puits.setPieceSuivante(tetromino2);

        JFrame frame = new JFrame("Falling Blox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(vuePuits, BorderLayout.CENTER);
        frame.add(panneauInformation, BorderLayout.EAST);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
