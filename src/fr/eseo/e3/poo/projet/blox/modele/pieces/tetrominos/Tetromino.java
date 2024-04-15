package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public abstract class Tetromino implements Piece {
    private Element[] elements;

    public Tetromino(Coordonnees coordonnees, Couleur couleur) {
        this.elements = new Element[4];
        setElements(coordonnees, couleur);
    }

    public Element[] getElements() {
        return elements;
    }

    @Override
    public void setPosition(int abscisse, int ordonnee) {
        elements[0].setCoordonnees(new Coordonnees(abscisse, ordonnee));  // set les news coordonnees à l'element 0
        setElements(getElements()[0].getCoordonnees(), getElements()[0].getCouleur()); // set maintenant les autres elemetns autour de la place du nouvel element
    }

    protected abstract void setElements(Coordonnees coordonnees, Couleur couleur);

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName()).append(" :\n");
        for (Element element : elements) {
            result.append("\t").append(element).append("\n");
        }
        return result.toString();
    }
}