package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;

import java.util.Objects;

public class Element {
    private Coordonnees coordonnees;
    private Couleur couleur;

    public Element(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
        this.couleur = Couleur.ROUGE;
    }

    public Element(int abscisse, int ordonnee) {
        this.coordonnees = new Coordonnees(abscisse, ordonnee);
        this.couleur = Couleur.ROUGE;
    }

    public Element(Coordonnees coordonnees, Couleur couleur) {
        this.coordonnees = coordonnees;
        this.couleur = couleur;
    }

    public Element(int abscisse, int ordonnee, Couleur couleur) {
        this.coordonnees = new Coordonnees(abscisse, ordonnee);
        this.couleur = couleur;
    }

    public Coordonnees getCoordonnees() {
        return this.coordonnees;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return coordonnees + " - " + couleur;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Element))
            return false;
        Element that = (Element) obj;
        return coordonnees.equals(that.coordonnees) && couleur.equals(that.couleur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordonnees, couleur);
    }

    public void deplacerDe(int deltaX, int deltaY) {
        int nouvelleAbscisse = this.coordonnees.getAbscisse() + deltaX;
        int nouvelleOrdonnee = this.coordonnees.getOrdonnee() + deltaY;
        this.coordonnees = new Coordonnees(nouvelleAbscisse, nouvelleOrdonnee);
    }
}
