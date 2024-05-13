package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;

import org.junit.jupiter.api.Test;

public class OTetrominoTest {
    @Test
    void testGetSet() {
        Coordonnees coordonnees = new Coordonnees(4, 4);
        Couleur couleur = Couleur.ORANGE;
        OTetromino tetromino = new OTetromino(coordonnees, couleur);

        Element[] elements = tetromino.getElements();
        assertEquals(elements.length, 4);

        assertEquals(coordonnees, elements[0].getCoordonnees());
        assertEquals(couleur, elements[0].getCouleur());

        assertEquals(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()),elements[1].getCoordonnees());
        assertEquals(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 1),elements[2].getCoordonnees());
        assertEquals(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() - 1),elements[3].getCoordonnees());

        assertEquals(couleur, elements[1].getCouleur());
        assertEquals(couleur, elements[2].getCouleur());
        assertEquals(couleur, elements[3].getCouleur());
    }

    @Test
    public void testToString() {
        Coordonnees coordonnees = new Coordonnees(5, 5);
        Couleur couleur = Couleur.ROUGE;
        OTetromino tetromino = new OTetromino(coordonnees, couleur);

        assertEquals("OTetromino :\n\t(5, 5) - ROUGE\n\t(6, 5) - ROUGE\n\t(5, 4) - ROUGE\n\t(6, 4) - ROUGE\n", tetromino.toString());
    }

    @Test
    public void testDeplacerDe_ValidMovement() {
        OTetromino oTetromino = new OTetromino(new Coordonnees(0, 0), Couleur.ROUGE);
        oTetromino.deplacerDe(0, 1);
        Element[] elements = oTetromino.getElements();
        assertEquals(new Coordonnees(0, 1), elements[0].getCoordonnees());
        assertEquals(new Coordonnees(1, 1), elements[1].getCoordonnees());
        assertEquals(new Coordonnees(0, 0), elements[2].getCoordonnees());
        assertEquals(new Coordonnees(1, 0), elements[3].getCoordonnees());
    }

    @Test
    public void testDeplacerDe_InvalidMovement() {
        OTetromino oTetromino = new OTetromino(new Coordonnees(0, 0), Couleur.ROUGE);
        assertThrows(IllegalArgumentException.class, () -> {
            oTetromino.deplacerDe(2, 1);
        });
    }
}
