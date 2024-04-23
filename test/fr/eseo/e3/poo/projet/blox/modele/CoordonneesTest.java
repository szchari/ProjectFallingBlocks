package fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CoordonneesTest {

    @Test
    public void testGetSet() {
        Coordonnees coord = new Coordonnees(5, 15);
        assertEquals(5, coord.getAbscisse(),"Mauvaise abscisse depuis get");
        assertEquals(15, coord.getOrdonnee(),"Mauvaise ordonnee depuis get");

        coord.setAbscisse(9);
        coord.setOrdonnee(18);
        assertEquals(9, coord.getAbscisse(),"Mauvaise abscisse depuis get après un set");
        assertEquals(18, coord.getOrdonnee(),"Mauvaise ordonnee depuis get après un set");
    }

    @Test
    public void testToString() {
        Coordonnees coord = new Coordonnees(5, 15);
        assertEquals("(5, 15)", coord.toString(),"ToString erroné");
    }

    @Test
    public void testEquals() {
        Coordonnees coord1 = new Coordonnees(5, 15);
        Coordonnees coord2 = new Coordonnees(5, 15);
        Coordonnees coord3 = new Coordonnees(9, 18);

        assertEquals(coord1, coord2,"coord1 n'est pas égal à coord2");
        assertEquals(coord2, coord1,"coord1 n'est pas égal à coord2");
        assertNotEquals(coord1, coord3);
        assertEquals(coord1.hashCode(), coord2.hashCode(),"Le hashcode de coord1 n'est pas égal à celui de coord2");
        assertNotEquals(coord1.hashCode(), coord3.hashCode());

        //assertEquals(coord1, coord1,"coord1 n'est pas égal à coord1 (oula)");
        assertEquals(coord1.hashCode(), coord1.hashCode(),"Le hashcode de coord1 n'est pas égal au hashcode de coord1");

        //assertEquals(coord2, coord2,"coord2 n'est pas égal à coord2");
        assertEquals(coord2.hashCode(), coord2.hashCode(),"Le hashcode de coord2 n'est pas égal au hashcode de coord2");

        assertEquals(coord1.equals(coord3), coord3.equals(coord1),"Ne retourne pas tous les deux la meme valeur boolean");
        assertNotEquals(coord1, coord3, "coord1 est égal à coord3");

        assertNotEquals(null, coord1, "coord est pas égal à null");
    }
}

