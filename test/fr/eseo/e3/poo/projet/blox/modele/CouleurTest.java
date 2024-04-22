package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.vue.VuePiece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

public class CouleurTest {
    @Test
    public void testGetCouleurPourAffichage() {
        assertEquals(Color.RED, Couleur.ROUGE.getCouleurPourAffichage());
        assertEquals(Color.ORANGE, Couleur.ORANGE.getCouleurPourAffichage());
        assertEquals(Color.BLUE, Couleur.BLEU.getCouleurPourAffichage());
        assertEquals(Color.GREEN, Couleur.VERT.getCouleurPourAffichage());
        assertEquals(Color.YELLOW, Couleur.JAUNE.getCouleurPourAffichage());
        assertEquals(Color.CYAN, Couleur.CYAN.getCouleurPourAffichage());
        assertEquals(Color.MAGENTA, Couleur.VIOLET.getCouleurPourAffichage());
    }

    @Test
    public void testTeinte() { // **
        Color couleur = new Color(100, 150, 200);

        Color couleurTeintee = VuePiece.teinte(couleur);

        int redAttendu = (int) (100 + (255 - 100) * 0.3);
        int greenAttendu = (int) (150 + (255 - 150) * 0.3);
        int blueAttendu = (int) (200 + (255 - 200) * 0.3);

        assertEquals(redAttendu, couleurTeintee.getRed());
        assertEquals(greenAttendu, couleurTeintee.getGreen());
        assertEquals(blueAttendu, couleurTeintee.getBlue());
    }
}
