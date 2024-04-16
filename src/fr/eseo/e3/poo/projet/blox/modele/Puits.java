package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public class Puits {
    public static final int LARGEUR_PAR_DEFAUT = 15;
    public static final int PROFONDEUR_PAR_DEFAUT = 25;
    private int largeur;
    private int profondeur;
    private Piece pieceActuelle;
    private Piece pieceSuivante;

    public Puits() {
        this.largeur = LARGEUR_PAR_DEFAUT;
        this.profondeur = PROFONDEUR_PAR_DEFAUT;
    }
    public Puits(int largeur, int profondeur) {
        if (largeur < 5 || largeur > LARGEUR_PAR_DEFAUT || profondeur < 15 || profondeur > PROFONDEUR_PAR_DEFAUT) {
            throw new IllegalArgumentException("La valeur de la largeur doit être comprise entre "
                    + 5 + " et " + LARGEUR_PAR_DEFAUT + " et pour la profondeur entre "
                    + 15 + " et " + PROFONDEUR_PAR_DEFAUT + ".");
        }
        this.largeur = largeur;
        this.profondeur = profondeur;
    }
    public Piece getPieceActuelle() {
        return pieceActuelle;
    }
    public Piece getPieceSuivante() {
        return pieceSuivante;
    }
    public int getProfondeur() {
        return this.profondeur;
    }
    public int getLargeur() {
        return this.largeur;
    }
    public void setPieceSuivante(Piece piece) {
        if (this.pieceSuivante != null) {
            this.pieceActuelle = this.pieceSuivante;
            this.pieceActuelle.setPosition(this.largeur / 2, -4);
        }
        this.pieceSuivante = piece;
    }
    public void setProfondeur(int profondeur) {
        if (profondeur < 15 || profondeur > PROFONDEUR_PAR_DEFAUT) {
            throw new IllegalArgumentException("La valeur de la profondeur doit être comprise entre 15 et " + PROFONDEUR_PAR_DEFAUT + ".");
        }
        this.profondeur = profondeur;
    }
    public void setLargeur(int largeur) {
        if (largeur < 5 || largeur > LARGEUR_PAR_DEFAUT) {
            throw new IllegalArgumentException("La valeur de la largeur doit être comprise entre 5 et " + LARGEUR_PAR_DEFAUT + ".");
        }
        this.largeur = largeur;
    }

    @Override
    public String toString() {
        String result = "Puits : Dimension " + this.largeur + " x " + this.profondeur + "\n";

        if (this.pieceActuelle == null) {
            result += "Piece Actuelle : <aucune>\n";
        } else {
            result += "Piece Actuelle : " + this.pieceActuelle.getClass().getSimpleName() + " :\n";
            for (Element element : this.pieceActuelle.getElements()) {
                result += "\t" + element + "\n";
            }
        }

        if (this.pieceSuivante == null) {
            result += "Piece Suivante : <aucune>\n";
        } else {
            result += "Piece Suivante : " + this.pieceSuivante.getClass().getSimpleName() + " :\n";
            for (Element element : this.pieceSuivante.getElements()) {
                result += "\t" + element + "\n";
            }
        }
        return result;
    }

}
