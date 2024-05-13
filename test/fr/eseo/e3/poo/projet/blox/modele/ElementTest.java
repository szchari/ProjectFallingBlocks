package fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ElementTest {

    @Test
    public void testGetSet() {
        int abscisse = 5;
        int ordonnee = 15;
        Element elem = new Element(abscisse,ordonnee, Couleur.JAUNE);
        Coordonnees coord1 = new Coordonnees(abscisse, ordonnee);

        assertEquals(coord1, elem.getCoordonnees(), "Mauvaise coordonnées");
        assertEquals(Couleur.JAUNE, elem.getCouleur(), "Mauvaise couleur");

        Coordonnees coord2 = new Coordonnees(2, 4);
        elem.setCoordonnees(coord2);
        elem.setCouleur(Couleur.BLEU);

        assertEquals(coord2, elem.getCoordonnees(), "Mauvaise coordonnées après un set");
        assertEquals(Couleur.BLEU, elem.getCouleur(), "Mauvaise couleur après un set");
    }

    @Test
    public void testToString() {
        int abscisse = 7;
        int ordonnee = 14;
        Element elem = new Element(abscisse,ordonnee, Couleur.VERT);

        assertEquals("(7, 14) - VERT", elem.toString(), "toString erroné");
    }

    @Test
    public void testEquals() {
        int abscisse = 7;
        int ordonnee = 14;
        int abscisse4 = 9;
        int ordonnee4 = 18;
        Element elem1 = new Element(abscisse,ordonnee, Couleur.VERT);
        Element elem2 = new Element(abscisse,ordonnee, Couleur.VERT);
        Element elem3 = new Element(abscisse,ordonnee, Couleur.JAUNE);
        Element elem4 = new Element(abscisse4,ordonnee4, Couleur.BLEU);

        assertEquals(elem1, elem2);
        assertNotEquals(elem1, elem3);
        assertNotEquals(elem1, elem4);
        assertEquals(elem1.hashCode(), elem2.hashCode());
        assertNotEquals(elem1.hashCode(), elem3.hashCode());

        assertNotEquals(null, elem1);
    }

    @Test
    public void testDeplacerDeValidMovement() {
        Element element = new Element(new Coordonnees(0, 0));
        element.deplacerDe(1, 1);
        assertEquals(new Coordonnees(1, 1), element.getCoordonnees());
    }
}
