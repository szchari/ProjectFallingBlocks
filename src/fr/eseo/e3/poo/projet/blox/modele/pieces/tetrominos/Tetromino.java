package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Tas;

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

    private void verifDeplacement(int deltaX2, int deltaY2) {
        if (Math.abs(deltaX2) > 1 || deltaY2 < 0 || deltaY2 > 1) {
            throw new IllegalArgumentException("Déplacement invalide : deltaX doit être -1, 0 ou 1 et deltaY doit être 1.");
        }
    }

    /*@Override
    public void deplacerDe(int deltaX, int deltaY) {
        try {
            // Vérifier si le déplacement est valide
            verifDeplacement(deltaX, deltaY);

            for (Element element : elements) {
                int nouvelleAbscisse = element.getCoordonnees().getAbscisse() + deltaX;
                int nouvelleOrdonnee = element.getCoordonnees().getOrdonnee() + deltaY;

                if (nouvelleAbscisse < 0 || nouvelleAbscisse >= puits.getLargeur() || nouvelleOrdonnee >= puits.getProfondeur()) {
                    throw new BloxException("Déplacement invalide : sortie du puits détectée.", BloxException.BLOX_SORTIE_PUITS);
                }
                if (puits.getTas().elementExists(nouvelleAbscisse, nouvelleOrdonnee)) {
                    throw new BloxException("Déplacement invalide : collision détectée.", BloxException.BLOX_COLLISION);
                }

                element.deplacerDe(deltaX, deltaY);
            }
        } catch (BloxException e) {
            // Gérer l'exception BloxException ici
            // Afficher un message d'erreur ou effectuer d'autres actions nécessaires
            System.err.println("Erreur BloxException : " + e.getMessage());
        }
    }*/

    @Override
    public void deplacerDe(int deltaX, int deltaY) {
        if (Math.abs(deltaX) > 1 || deltaY < 0 || deltaY > 1) {
            throw new IllegalArgumentException("Déplacement invalide : deltaX doit être -1, 0 ou 1 et deltaY doit être 1.");
        }
        // debugerino
        System.out.println("Je me déplace de "+deltaX);

        for(Element element : this.elements) {
            element.deplacerDe(deltaX, deltaY);
        }
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

            // Appliquer la rotation vrai/faux ? si vrai : si faux
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