package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;
import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Puits puits = new Puits();
        puits.setPieceSuivante(new OTetromino(new Coordonnees(3, 3), Couleur.ROUGE));

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

    //test de tas
    @Test
    public void testTasInitialise() {
        Puits puits = new Puits();
        assertNotNull(puits.getTas(), "Le tas ne doit pas être null.");
        assertTrue(puits.getTas().getElements().isEmpty(), "Le tas doit être initialisé vide.");
    }

    // test de gravite (sans succès)
    @Test
    public void testGravitePieceTombeSansCollision() {
        Puits puits = new Puits();
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_PIECE);
        Tetromino tetromino = UsineDePiece.genererTetromino();
        Tetromino tetromino2 = UsineDePiece.genererTetromino();
        puits.setPieceSuivante(tetromino);
        puits.setPieceSuivante(tetromino2);
        puits.gravite();
    }
}
