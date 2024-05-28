package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.BloxException;

import java.util.ArrayList;
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

    @Override // j'ai dû faire des méthodes séparés à cause de la compléxité cycolmatique..
    public void deplacerDe(int deltaX, int deltaY) throws BloxException {
        // vérifier si le déplacement est valide
        verifDeplacement(deltaX, deltaY);

        // vérifier les nouvelles coordonnées des éléments après déplacement
        verifNewcoord(deltaX, deltaY);

        // si toutes les vérifications passent, déplacer les éléments
        for (Element element : elements) {
          element.deplacerDe(deltaX, deltaY);
        }
    }

    private void verifDeplacement(int deltaX2, int deltaY2) {
        if (Math.abs(deltaX2) > 1 || deltaY2 < 0 || deltaY2 > 1) {
            throw new IllegalArgumentException("Déplacement invalide : deltaX doit être -1, 0 ou 1 et deltaY doit être 1.");
        }
    }

    private void verifNewcoord(int deltaX, int deltaY) throws BloxException {
        for (Element element : elements) {
            int nouvelleAbscisse = element.getCoordonnees().getAbscisse() + deltaX;
            int nouvelleOrdonnee = element.getCoordonnees().getOrdonnee() + deltaY;
            if (getPuits() != null) {
                // vérifier si la nouvelle position sort du puits
                if (nouvelleAbscisse < 0 || nouvelleAbscisse >= puits.getLargeur()) {
                    throw new BloxException("Déplacement invalide : sortie du puits détectée.", BloxException.BLOX_SORTIE_PUITS);
                }

                // vérifier si la nouvelle position entre en collision avec un élément du tas
                if (puits.getTas().elementExists(nouvelleAbscisse, nouvelleOrdonnee) || nouvelleOrdonnee >= puits.getProfondeur()) {
                    throw new BloxException("Déplacement invalide : collision détectée.", BloxException.BLOX_COLLISION);
                }
            }
        }
    }

    @Override // j'ai dû faire des méthodes séparés à cause de la compléxité cycolmatique..
    public void tourner(boolean sensHoraire) throws BloxException {
        Coordonnees coordRef = getElements()[0].getCoordonnees();

        // étape 1: On sauvegarde la position initiale de la pièce qui veut être tourner
        int ancienneAbscisse = coordRef.getAbscisse();
        int ancienneOrdonnee = coordRef.getOrdonnee();

        List<Coordonnees> nouvellesCoordonnees = new ArrayList<>();
        List<Coordonnees> anciennesCoordonnees = new ArrayList<>();

        try {
            // étape 2: Translater les éléments de la pièce pour placer l'élément de référence à l'origine 0,0
            anciennesCoordonnees = anciennesCoord(ancienneAbscisse, ancienneOrdonnee);

            // étape 3: Calculer les futures nouvelles coordonnées après la rotation
            nouvellesCoordonnees = nouvellesCoord(anciennesCoordonnees, sensHoraire);

            // vérifier les nouvelles coordonnées pour les collisions et les sorties du puits
            checklesCollisions(nouvellesCoordonnees, ancienneAbscisse, ancienneOrdonnee);

            // si toutes les vérifications passent on applique les nouvelles coordonnées
            int i = 0;
            for (Element element : getElements()) {
                Coordonnees nouvelleCoord = nouvellesCoordonnees.get(i);
                int nouvelleAbscisse = nouvelleCoord.getAbscisse() + ancienneAbscisse;
                int nouvelleOrdonnee = nouvelleCoord.getOrdonnee() + ancienneOrdonnee;
                element.setCoordonnees(new Coordonnees(nouvelleAbscisse, nouvelleOrdonnee));
                i++;
            }
            System.out.println("Breakpoint 4 fin");
        } catch (BloxException e) {
            // si jamais l'exception proc, on replace la pièce (ses éléments) à leurs états initiaux
            int i = 0;
            for (Element element : getElements()) {
                Coordonnees anciennesCoord = anciennesCoordonnees.get(i);
                element.setCoordonnees(anciennesCoord);
                i++;
            }
            throw e; // rethrow l'expression
        }
    }

    private List<Coordonnees> anciennesCoord(int ancienneAbscisse, int ancienneOrdonnee) {
        // liste pour sauvegarder les anciennes coordonnées
        List<Coordonnees> anciennesCoordonnees = new ArrayList<>();
        for (Element element : getElements()) {
            Coordonnees ancienneCoord = element.getCoordonnees();
            anciennesCoordonnees.add(ancienneCoord);

            int nouvelleAbscisse = ancienneCoord.getAbscisse() - ancienneAbscisse;
            int nouvelleOrdonnee = ancienneCoord.getOrdonnee() - ancienneOrdonnee;
            element.setCoordonnees(new Coordonnees(nouvelleAbscisse, nouvelleOrdonnee));
        }
        System.out.println("Breakpoint 1");
        return anciennesCoordonnees;
    }

    private List<Coordonnees> nouvellesCoord(List<Coordonnees> anciennesCoordonnees, boolean sensHoraire) {
        // liste pour stocker les nouvelles coordonnées après la rotation
        List<Coordonnees> nouvellesCoordonnees = new ArrayList<>();
        for (Element element : getElements()) {
            int ancienneAbscisseElement = element.getCoordonnees().getAbscisse();
            int ancienneOrdonneeElement = element.getCoordonnees().getOrdonnee();

            // appliquer la rotation avec l'opérateur ternaire
            int nouvelleAbscisse = sensHoraire ? -ancienneOrdonneeElement : ancienneOrdonneeElement;
            int nouvelleOrdonnee = sensHoraire ? ancienneAbscisseElement : -ancienneAbscisseElement;

            nouvellesCoordonnees.add(new Coordonnees(nouvelleAbscisse, nouvelleOrdonnee));
        }
        System.out.println("Breakpoint 2");
        return nouvellesCoordonnees;
    }

    private void checklesCollisions(List<Coordonnees> nouvellesCoordonnees, int ancienneAbscisse, int ancienneOrdonnee) throws BloxException {
        for (Coordonnees coord : nouvellesCoordonnees) {
            int nouvelleAbscisse = coord.getAbscisse() + ancienneAbscisse;
            int nouvelleOrdonnee = coord.getOrdonnee() + ancienneOrdonnee;

            if (getPuits() != null) {
                if (nouvelleAbscisse < 0 || nouvelleAbscisse >= puits.getLargeur()) {
                    throw new BloxException("Rotation invalide : sortie du puits détectée.", BloxException.BLOX_SORTIE_PUITS);
                }

                if (puits.getTas().elementExists(nouvelleAbscisse, nouvelleOrdonnee) || nouvelleOrdonnee >= puits.getProfondeur()) {
                    throw new BloxException("Rotation invalide : collision détectée.", BloxException.BLOX_COLLISION);
                }
            }
        }
        System.out.println("Breakpoint 3");
    }
}