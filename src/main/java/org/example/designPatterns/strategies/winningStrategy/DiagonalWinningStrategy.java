package org.example.designPatterns.strategies.winningStrategy;

import org.example.entities.Board;
import org.example.entities.Move;

public class DiagonalWinningStrategy implements WinningStrategy{
    @Override
    public Boolean checkWinner(Board board, Move move) {
        return null;
    }

    @Override
    public void handleUndo(Board board, Move move) {

    }
}
