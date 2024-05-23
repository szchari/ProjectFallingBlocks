package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;

import org.junit.jupiter.api.Test;

public class ITetrominoTest {
    @Test
    void testGetSet() {
        Coordonnees coordonnees = new Coordonnees(0, 0);
        Couleur couleur = Couleur.CYAN;
        ITetromino itetromino = new ITetromino(coordonnees, couleur);

        itetromino.setElements(coordonnees, couleur);

        Element[] elements = itetromino.getElements();
        assertEquals(4, elements.length);

        assertEquals(coordonnees, elements[0].getCoordonnees());
        assertEquals(couleur, elements[0].getCouleur());

        assertEquals(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 1),elements[1].getCoordonnees());
        assertEquals(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 2),elements[2].getCoordonnees());
        assertEquals(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 1),elements[3].getCoordonnees());

        assertEquals(couleur, elements[1].getCouleur());
        assertEquals(couleur, elements[2].getCouleur());
        assertEquals(couleur, elements[3].getCouleur());
    }

    @Test
    public void testToString() {
        Coordonnees coordonnees = new Coordonnees(5, 5);
        Couleur couleur = Couleur.VIOLET;
        ITetromino itetromino = new ITetromino(coordonnees, couleur);

        assertEquals("ITetromino :\n\t(5, 5) - VIOLET\n\t(5, 4) - VIOLET\n\t(5, 3) - VIOLET\n\t(5, 6) - VIOLET\n", itetromino.toString());
    }

    @Test
    public void testDeplacerDeValidMovement() {
        ITetromino iTetromino = new ITetromino(new Coordonnees(0, 0), Couleur.ROUGE);
        try {
            iTetromino.deplacerDe(0, 1);
        } catch (BloxException e) {
            System.err.println("Erreur de déplacement : " + e.getMessage());
        }
        Element[] elements = iTetromino.getElements();
        assertEquals(new Coordonnees(0, 1), elements[0].getCoordonnees());
        assertEquals(new Coordonnees(0, 0), elements[1].getCoordonnees());
        assertEquals(new Coordonnees(0, -1), elements[2].getCoordonnees());
        assertEquals(new Coordonnees(0, 2), elements[3].getCoordonnees());
    }

    @Test
    public void testDeplacerDeInvalidMovement() {
        ITetromino iTetromino = new ITetromino(new Coordonnees(0, 0), Couleur.ROUGE);
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                iTetromino.deplacerDe(2, 1);
            } catch (BloxException e) {
                System.err.println("Erreur de déplacement : " + e.getMessage());
            }
        });
    }
}