package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;

public class OTetromino
        extends Tetromino {

    public OTetromino(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

    @Override
    public void tourner(boolean sensHoraire) {
        // les OTetrominos ne peuvent pas être tourné
    }

    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        getElements()[0] = new Element(coordonnees.getAbscisse(), coordonnees.getOrdonnee(), couleur);
        getElements()[1] = new Element(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee(), couleur);
        getElements()[2] = new Element(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 1, couleur);
        getElements()[3] = new Element(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() - 1, couleur);
    }
}