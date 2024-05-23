package fr.eseo.e3.poo.projet.blox.modele;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Tas {
    private List<Element> elements;
    private Puits puits;

    public Tas(Puits puits) {
        this.puits = puits;
        this.elements = new ArrayList<>();
    }

    public Tas(Puits puits, int nbElements) {
        this(puits);
        int nbLignes = (nbElements / puits.getLargeur()) + 1;
        construireTas(nbElements, nbLignes, new Random());
    }

    public Tas(Puits puits, int nbElements, int nbLignes) {
        this(puits);
        construireTas(nbElements, nbLignes, new Random());
    }

    public Tas(Puits puits, int nbElements, int nbLignes, Random random) {
        this(puits);
        construireTas(nbElements, nbLignes, random);
    }

    public Puits getPuits() {
        return this.puits;
    }
    public List<Element> getElements() {
        return elements;
    }

    public boolean elementExists(int x, int y) {
        for (Element element : elements) {
            if (element.getCoordonnees().getAbscisse() == x && element.getCoordonnees().getOrdonnee() == y) {
                return true;
            }
        }
        return false;
    }
    private void construireTas(int nbElements, int nbLignes, Random rand) {
        if (nbElements <= 0 || nbElements > puits.getLargeur() * nbLignes) {
            throw new IllegalArgumentException("Trop d'éléments pour le nombre de lignes spécifié.");
        }

        int largeur = puits.getLargeur();
        int profondeur = puits.getProfondeur();
        int nbElementsPlaces = 0;

        while (nbElementsPlaces < nbElements) {
            int y = profondeur - rand.nextInt(nbLignes) - 1;
            int x = rand.nextInt(largeur);

            if (!elementExists(x, y)) {
                Couleur[] couleurs = Couleur.values();
                int indiceCouleur = rand.nextInt(couleurs.length);
                Couleur couleur = couleurs[indiceCouleur];

                elements.add(new Element(new Coordonnees(x, y), couleur));
                nbElementsPlaces++;
            }
        }
    }
}
