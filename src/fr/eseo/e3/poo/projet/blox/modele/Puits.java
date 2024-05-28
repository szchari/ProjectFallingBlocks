package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.UsineDePiece;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class Puits {
    public static final int LARGEUR_PAR_DEFAUT = 15;
    public static final int PROFONDEUR_PAR_DEFAUT = 25;
    public static final String MODIFICATION_PIECE_ACTUELLE = "Modification de la pièce actuelle";
    public static final String MODIFICATION_PIECE_SUIVANTE = "Modification de la pièce suivante";
    private int largeur;
    private int profondeur;
    private Piece pieceActuelle;
    private Piece pieceSuivante;
    private PropertyChangeSupport pcs;
    private Tas tas;

    public Puits() {
        this.largeur = LARGEUR_PAR_DEFAUT;
        this.profondeur = PROFONDEUR_PAR_DEFAUT;
        this.tas = new Tas(this);
        this.pcs = new PropertyChangeSupport(this); // initialise pcs
    }

    public Puits(int largeur, int profondeur) {
        if (largeur < 5 || largeur > LARGEUR_PAR_DEFAUT || profondeur < 15 || profondeur > PROFONDEUR_PAR_DEFAUT) {
            throw new IllegalArgumentException("La valeur de la largeur doit être comprise entre "
                    + 5 + " et " + LARGEUR_PAR_DEFAUT + " et pour la profondeur entre "
                    + 15 + " et " + PROFONDEUR_PAR_DEFAUT + ".");
        }
        this.largeur = largeur;
        this.profondeur = profondeur;
        this.tas = new Tas(this);
        this.pcs = new PropertyChangeSupport(this);
    }

    public Puits(int largeur, int profondeur, int nbElements, int nbLignes) {
        this.largeur = largeur;
        this.profondeur = profondeur;
        if (nbElements > largeur * nbLignes) {
            throw new IllegalArgumentException("Trop d'éléments pour le nombre de lignes spécifié.");
        }
        this.tas = new Tas(this, nbElements, nbLignes);
        this.pcs = new PropertyChangeSupport(this);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
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
    public void setPieceSuivante(Piece pieceSuivanteLocal) {
        if (this.getPieceSuivante() != null){
            pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, this.pieceActuelle, this.pieceSuivante);
            this.pieceActuelle = this.pieceSuivante;
            this.pieceActuelle.setPosition(this.getLargeur() / 2, -4);
            this.pieceSuivante.setPuits(this);
        }
        pcs.firePropertyChange(MODIFICATION_PIECE_SUIVANTE, this.pieceSuivante, pieceSuivanteLocal);
        this.pieceSuivante = pieceSuivanteLocal;
        this.pieceSuivante.setPuits(this);
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

    public Tas getTas() {
        return this.tas;
    }
    public void setTas(Tas tas) {
        this.tas = tas;
    }

    private void gererCollision() {
        this.tas.ajouterElements(this.pieceActuelle);
        //setPieceSuivante(UsineDePiece.genererTetromino()); // fais marcher mon jeu mais pas l'assignement center
        this.pieceActuelle = pieceSuivante; // fais marcher l'assignement center mais pas mon jeu
    }

    public void gravite() {
        try {
            this.pieceActuelle.deplacerDe(0, 1); // tenter de déplacer la pièce vers le bas
        } catch (BloxException e) {
            gererCollision();
        }
    }
}
