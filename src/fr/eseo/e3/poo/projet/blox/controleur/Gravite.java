package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Gravite
        implements ActionListener {
    private final VuePuits vuePuits;
    private final Puits puits;
    private javax.swing.Timer timer;
    public Gravite(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
        this.timer = new Timer(1000, this);
        this.timer.start();
    }

    public int getPeriodicite() {
        return timer.getDelay();
    }

    public void setPeriodicite(int periodicite) {
        timer.setDelay(periodicite);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        vuePuits.getPuits().gravite();
        vuePuits.repaint();
    }
}
