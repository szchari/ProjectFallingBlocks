package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TasTest {
    @Test
    public void testTasConstructeurParDefaut() {
        Puits puits = new Puits();
        Tas tas = new Tas(puits);
        assertTrue(tas.getElements().isEmpty(), "Le tas devrait être vide.");
    }

    @Test
    public void testTasConstructeurAvecNbElements() {
        Puits puits = new Puits();
        Tas tas = new Tas(puits, 10);
        assertEquals(10, tas.getElements().size(), "Le tas devrait contenir 10 éléments.");
    }

    @Test
    public void testTasConstructeurAvecNbElementsEtNbLignes() {
        Puits puits = new Puits();
        Tas tas = new Tas(puits, 10, 5);
        assertEquals(10, tas.getElements().size(), "Le tas devrait contenir 10 éléments.");
    }


    @Test
    public void testConstructeurException() {
        Puits puits = new Puits();
        assertThrows(IllegalArgumentException.class, () -> {
            new Tas(puits, 548552, 1);
        }, "Une exception devrait être lancée pour trop d'éléments.");
    }

    @Test
    public void testTasConstructeurAvecRandom() {
        Puits puits = new Puits();
        Random random = new Random(8535937);
        Tas tas = new Tas(puits, 1, 1, random);

        Element element = tas.getElements().get(0);
        assertEquals(new Coordonnees(4, 24), element.getCoordonnees(), "Les coordonnées de l'élément ne sont pas correctes.");
        assertEquals(Couleur.ROUGE, element.getCouleur(), "La couleur de l'élément ne correspond pas.");
    }

    @Test
    public void testTasConstructeurAvecRandomMultielement() {
        Puits puits = new Puits();
        Random random = new Random(8535937);
        Tas tas = new Tas(puits, 5, 2, random);

        List<Element> elements = tas.getElements();
        assertEquals(5, elements.size(), "Le nombre d'éléments générés n'est pas correct.");

        Coordonnees[] expectedCoordonnees = {
                new Coordonnees(4, 23),
                new Coordonnees(10, 24),
                new Coordonnees(0, 24),
                new Coordonnees(13, 23),
                new Coordonnees(14, 23),
        };
        Couleur[] expectedCouleurs = {
                Couleur.ROUGE,
                Couleur.CYAN,
                Couleur.ROUGE,
                Couleur.CYAN,
                Couleur.ROUGE,
        };

        for (int i = 0; i < expectedCoordonnees.length; i++) {
            assertEquals(expectedCoordonnees[i], elements.get(i).getCoordonnees(), "Les coordonnées de l'élément " + i + " ne sont pas correctes.");
            assertEquals(expectedCouleurs[i], elements.get(i).getCouleur(), "La couleur de l'élément " + i + " ne correspond pas.");
        }
    }

    @Test
    public void testAjouterElementsPieceNonVide() {
        Puits puits = new Puits();
        Tas tas = new Tas(puits, 4);
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_PIECE);
        Tetromino tetromino = UsineDePiece.genererTetromino();
        Tetromino tetromino2 = UsineDePiece.genererTetromino();
        puits.setPieceSuivante(tetromino);
        puits.setPieceSuivante(tetromino2);

        tas.ajouterElements(tetromino);

        assertEquals(8, tas.getElements().size(), "Le tas devrait contenir 8 éléments après ajout de la pièce.");
    }

    @Test
    public void testAjouterElementsPieceVide() {
        Puits puits = new Puits();
        Tas tas = new Tas(puits, 4);
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_PIECE);
        Tetromino tetromino = UsineDePiece.genererTetromino();
        Tetromino tetromino2 = UsineDePiece.genererTetromino();
        puits.setPieceSuivante(tetromino);
        puits.setPieceSuivante(tetromino2);

        tas.ajouterElements(tetromino);

        assertEquals(8, tas.getElements().size(), "Le tas devrait contenir 8 éléments après ajout de la pièce.");
    }
}
