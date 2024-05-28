package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;

import java.util.Random;

public class UsineDePiece {
    public static final int ALEATOIRE_PIECE = 3;
    public static final int ALEATOIRE_COMPLET = 2;
    public static final int CYCLIC = 1;
    private static int mode = ALEATOIRE_PIECE;
    private static int next = 0;
    private UsineDePiece() {}

    public static void setMode(int mode) {
        UsineDePiece.mode = mode;
    }

    public static Tetromino genererTetromino() {
        Tetromino tetromino = null;
        switch (mode) {
            case CYCLIC:
                tetromino = genererTetrominoCyclique();
                break;
            case ALEATOIRE_COMPLET:
                tetromino = genererTetrominoAleatoireComplet();
                break;
            case ALEATOIRE_PIECE:
                tetromino = genererTetrominoAleatoirePiece();
                break;
            default:
                // impossible
        }
        return tetromino;
    }

    public static Tetromino genererTetrominoCyclique() {
        if (next % 2 == 0) {
            next++;
            return new OTetromino(new Coordonnees(2, 3), Couleur.ROUGE);
        } else {
            next = 0;
            return new ITetromino(new Coordonnees(2, 3), Couleur.ORANGE);
        }
    }

    public static Tetromino genererTetrominoAleatoireComplet() {
        Random random = new Random();
        int randomType = random.nextInt(2);
        if (randomType == 0) {
            return new ITetromino(new Coordonnees(2, 3), Couleur.values()[random.nextInt(Couleur.values().length)]);
        } else {
            return new OTetromino(new Coordonnees(2, 3), Couleur.values()[random.nextInt(Couleur.values().length)]);
        }
    }

    public static Tetromino genererTetrominoAleatoirePiece() {
        Random random = new Random();
        int randomType = random.nextInt(2);
        if (randomType == 0) {
            return new ITetromino(new Coordonnees(2, 3), Couleur.ORANGE);
        } else {
            return new OTetromino(new Coordonnees(2, 3), Couleur.ROUGE);
        }
    }
}
