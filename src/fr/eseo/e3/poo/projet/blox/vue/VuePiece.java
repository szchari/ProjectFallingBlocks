package fr.eseo.e3.poo.projet.blox.vue;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Arrays;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public class VuePiece {
    public static final double MULTIPLIER_TEINTE = 0.3;
    private final Piece piece;
    private final int taille;

    public VuePiece(Piece piece, int taille) {
        this.piece = piece;
        this.taille = taille;
    }

    public static Color teinte(Color couleur) {
        int red = couleur.getRed();
        int green = couleur.getGreen();
        int blue = couleur.getBlue();

        red = (int) (red + (255 - red) * MULTIPLIER_TEINTE);
        green = (int) (green + (255 - green) * MULTIPLIER_TEINTE);
        blue = (int) (blue + (255 - blue) * MULTIPLIER_TEINTE);

        return new Color(red, green, blue);
    }

    public void afficherPiece(Graphics2D g2D) {
        List<Element> elements = Arrays.asList(piece.getElements()); // pour convertir les elements en une liste

        if (!elements.isEmpty()) {  // si la liste element n'est pas vide alors
            Color couleurAWT = elements.get(0).getCouleur().getCouleurPourAffichage();  // on get la couleur du 1er element
            Color teinteClair = teinte(couleurAWT); // on lui apply la méthode teinte

            for (Element element : elements) {
                Coordonnees coordonnees = element.getCoordonnees();
                int abscisse = coordonnees.getAbscisse() * taille;
                int ordonnee = coordonnees.getOrdonnee() * taille;
                // teinte clair seulement pour le premier elem :
                Color couleur = (element == elements.get(0)) ? teinteClair : element.getCouleur().getCouleurPourAffichage();

                g2D.setColor(couleur);
                g2D.fill3DRect(abscisse, ordonnee, taille, taille, true);
            }
        }
    }
}
