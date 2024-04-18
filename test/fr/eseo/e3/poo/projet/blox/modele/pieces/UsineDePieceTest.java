package fr.eseo.e3.poo.projet.blox.modele.pieces;

import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UsineDePieceTest {
    @Test
    public void testGenererTetrominoCyclique() {
        UsineDePiece.setMode(UsineDePiece.CYCLIC);
        Tetromino tetromino1 = UsineDePiece.genererTetromino();
        Tetromino tetromino2 = UsineDePiece.genererTetromino();

        assertEquals(tetromino1.getClass(), OTetromino.class);
        assertEquals(tetromino2.getClass(), ITetromino.class);
    }

    @Test
    public void testGenererTetrominoAleatoire() {
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_COMPLET);
        Tetromino tetromino1 = UsineDePiece.genererTetromino();
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_PIECE);
        Tetromino tetromino2 = UsineDePiece.genererTetromino();

        assertNotEquals(tetromino1.getClass(), tetromino2.getClass());
    }
}
