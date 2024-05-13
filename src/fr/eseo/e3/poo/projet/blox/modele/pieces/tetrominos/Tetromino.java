package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.Puits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Tetromino implements Piece {
    private Element[] elements;

    private Puits puits;
    @Override
    public Puits getPuits() {
        return puits;
    }
    @Override
    public void setPuits(Puits puits) {
        this.puits = puits;
    }

    public Tetromino(Coordonnees coordonnees, Couleur couleur) {
        this.elements = new Element[4];
        setElements(coordonnees, couleur);
    }

    public Element[] getElements() {
        return elements;
    }

    @Override
    public void setPosition(int abscisse, int ordonnee) {
        // set les news coordonnees à l'element 0
        elements[0].setCoordonnees(new Coordonnees(abscisse, ordonnee));
        // set maintenant les autres elemetns autour de la place du nouvel element
        setElements(getElements()[0].getCoordonnees(), getElements()[0].getCouleur());
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

    @Override
    public void deplacerDe(int deltaX, int deltaY) {
        if (Math.abs(deltaX) > 1 && deltaX != 0 || deltaY != 1) {
            throw new IllegalArgumentException("Déplacement invalide : deltaX doit être -1, 0 ou 1 et deltaY doit être 1.");
        }

        getElements()[0].deplacerDe(deltaX, deltaY);
        setElements(getElements()[0].getCoordonnees(), getElements()[0].getCouleur());
    }

    @Override
    public void tourner(boolean sensHoraire) {
        // Obtenez la coordonnée de référence de la pièce
        Coordonnees coordRef = getElements()[0].getCoordonnees();

        // Étape 1: Sauvegarder la position actuelle de l'élément de référence
        int ancienneAbscisse = coordRef.getAbscisse();
        int ancienneOrdonnee = coordRef.getOrdonnee();

        // Étape 2: Translater les éléments de la pièce pour placer l'élément de référence à l'origine (0, 0)
        for (Element element : getElements()) {
            int nouvelleAbscisse = element.getCoordonnees().getAbscisse() - ancienneAbscisse;
            int nouvelleOrdonnee = element.getCoordonnees().getOrdonnee() - ancienneOrdonnee;
            element.setCoordonnees(new Coordonnees(nouvelleAbscisse, nouvelleOrdonnee));
        }

        // Récupérer une liste d'éléments à partir de la pièce
        List<Element> elementsList = new ArrayList<>(Arrays.asList(getElements()));

        // Étape 3: Effectuer la rotation des éléments (sauf la référence) autour de l'origine du repère
        for (int i = 1; i < elementsList.size(); i++) {
            Element element = elementsList.get(i);
            int ancienneAbscisseElement = element.getCoordonnees().getAbscisse();
            int ancienneOrdonneeElement = element.getCoordonnees().getOrdonnee();

            // Appliquer la rotation
            int nouvelleAbscisse = sensHoraire ? -ancienneOrdonneeElement : ancienneOrdonneeElement;
            int nouvelleOrdonnee = sensHoraire ? ancienneAbscisseElement : -ancienneAbscisseElement;

            element.setCoordonnees(new Coordonnees(nouvelleAbscisse, nouvelleOrdonnee));
        }

        // Étape 4: Translater les éléments de la pièce pour replacer l'élément de référence à sa place initiale
        for (Element element : getElements()) {
            int nouvelleAbscisse = element.getCoordonnees().getAbscisse() + ancienneAbscisse;
            int nouvelleOrdonnee = element.getCoordonnees().getOrdonnee() + ancienneOrdonnee;
            element.setCoordonnees(new Coordonnees(nouvelleAbscisse, nouvelleOrdonnee));
        }
    }
}