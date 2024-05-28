package fr.eseo.e3.poo.projet.blox;

import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.Tas;
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
            // Vérifier si le nombre d'éléments est négatif ou supérieur à 0
            if (nbElements < 0) {
                System.err.println("Le nombre d'éléments ne peut pas être négatif.");
                return; // Arrêter l'exécution
            }
        }

        Puits puits = new Puits();
        VuePuits vuePuits = new VuePuits(puits, taille);

        // Si un seul argument est passé, initialiser le Tas avec le nombre d'éléments spécifié
        if (args.length == 1) {
            Tas tas = new Tas(puits, nbElements);
        }

        PanneauInformation panneauInformation = new PanneauInformation(puits);

        JFrame frame = new JFrame("Falling Blox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(vuePuits, BorderLayout.CENTER);
        frame.add(panneauInformation, BorderLayout.EAST);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Créer le contrôleur de gravité et démarrer la chute de la pièce
        Gravite gravite = new Gravite(vuePuits);
    }
}
