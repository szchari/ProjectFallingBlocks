package fr.eseo.e3.poo.projet.blox.vue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.Puits;

public class VuePuitsTest {
    @Test
    public void testPuitsConstruct() {
        Puits puits = new Puits();
        VuePuits vuePuits = new VuePuits(puits);

        assertNotNull(vuePuits.getPuits());
        assertEquals(puits, vuePuits.getPuits());
        assertEquals(VuePuits.TAILLE_PAR_DEFAUT, vuePuits.getTaille());
    }
    @Test
    public void testPuitsTailleConstruct() {
        Puits puits = new Puits();
        int taille = 50;
        VuePuits vuePuits = new VuePuits(puits, taille);

        assertNotNull(vuePuits.getPuits());
        assertEquals(puits, vuePuits.getPuits());
        assertEquals(taille, vuePuits.getTaille());
    }
    @Test
    public void testSetPuits() {
        Puits puits1 = new Puits();
        Puits puits2 = new Puits();
        VuePuits vuePuits = new VuePuits(puits1);

        vuePuits.setPuits(puits2);

        assertNotNull(vuePuits.getPuits());
        assertEquals(puits2, vuePuits.getPuits());
    }
    @Test
    public void testSetTaille() {
        int taille1 = 30;
        int taille2 = 40;
        VuePuits vuePuits = new VuePuits(new Puits());

        vuePuits.setTaille(taille2);

        assertEquals(taille2, vuePuits.getTaille());
    }
}
