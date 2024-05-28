package fr.eseo.e3.poo.projet.blox.controleur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraviteTest {
    private Puits puits;
    private VuePuits vuePuits;
    private Gravite gravite;

    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        vuePuits = new VuePuits(puits, 30);
        gravite = new Gravite(vuePuits);
    }

    @Test
    public void testGetPeriodicite() {
        assertEquals(500, gravite.getPeriodicite());
    }

    @Test
    public void testSetPeriodicite() {
        gravite.setPeriodicite(500);
        assertEquals(500, gravite.getPeriodicite());
    }
}
