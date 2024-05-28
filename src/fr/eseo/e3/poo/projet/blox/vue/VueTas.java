package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Tas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

public class VueTas {
    public static final double MULTIPLIER_NUANCE = 0.4;
    private final VuePuits vuePuits;
    private final Tas tas;

    public VueTas(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        tas = vuePuits.getPuits().getTas();
    }
    public VuePuits getVuePuits() {
        return vuePuits;
    }


    public static Color nuance(Color couleur) {
        int red = couleur.getRed();
        int green = couleur.getGreen();
        int blue = couleur.getBlue();

        red = (int) (red * (1 - MULTIPLIER_NUANCE));
        green = (int) (green * (1 - MULTIPLIER_NUANCE));
        blue = (int) (blue * (1 - MULTIPLIER_NUANCE));

        return new Color(red, green, blue);
    }

    public void afficher(Graphics2D g2D) {
        int taille = vuePuits.getTaille();
        List<Element> elements = tas.getElements(); // Récupérer les éléments du tas

        for (Element element : elements) { // Parcourir la liste des éléments
            if (element != null) { // Vérifier si l'élément existe
                Coordonnees coordonnees = element.getCoordonnees();
                int abscisse = coordonnees.getAbscisse() * taille;
                int ordonnee = coordonnees.getOrdonnee() * taille;

                Color couleur = nuance(element.getCouleur().getCouleurPourAffichage());

                g2D.setColor(couleur);
                g2D.fill3DRect(abscisse, ordonnee, taille, taille, false);
            }
        }
    }

}
