package fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PuitsTest {
    @Test
    public void testGetSet() {
        Puits puit1 = new Puits();
        Puits puit2 = new Puits(12, 18);
        Puits puit3 = new Puits(10, 20);

        puit3.setLargeur(15);
        puit3.setProfondeur(25);

        assertNotEquals(puit1, puit2, "Problème plutôt étrange");
        assertEquals(puit1.getLargeur(), puit3.getLargeur(), "Largeurs non égales");
        assertEquals(puit1.getProfondeur(), puit3.getProfondeur(), "Profondeur non égales");
    }

    @Test
    public void testConstructeurAvecLargeurHorsLimites() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Puits(3, 20);
        }, "Largeur erronée malheureusement");
    }

    @Test
    public void testConstructeurAvecProfondeurHorsLimites() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Puits(10, 30);
        }, "Profondeur en dehors des limites imposées");
    }

    @Test
    public void testSetLargeurHorsLimites() {
        Puits puits = new Puits();
        assertThrows(IllegalArgumentException.class, () -> {
            puits.setLargeur(4);
        }, "Largeur du puit trop élevée !!");
    }

    @Test
    public void testSetProfondeurHorsLimites() {
        Puits puits = new Puits();
        assertThrows(IllegalArgumentException.class, () -> {
            puits.setProfondeur(26);
        }, "Profondeur du puit trop grande !");
    }

    @Test
    void testToString() {
        // Création d'une instance de Puits avec une pièce actuelle et une pièce suivante
        Puits puits = new Puits();
        puits.setPieceSuivante(new OTetromino(new Coordonnees(3, 3), Couleur.ROUGE));

        // Création de la chaîne de caractères attendue
        String expected = "Puits : Dimension 15 x 25\n" +
                "Piece Actuelle : <aucune>\n" +
                "Piece Suivante : OTetromino :\n" +
                "\t(3, 3) - ROUGE\n" +
                "\t(4, 3) - ROUGE\n" +
                "\t(3, 2) - ROUGE\n" +
                "\t(4, 2) - ROUGE\n";


        String actual = puits.toString();

        assertEquals(expected, actual);
    }
}
