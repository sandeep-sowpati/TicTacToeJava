package org.example.designPatterns.strategies.winningStrategy;

import org.example.entities.Board;
import org.example.entities.Cell;
import org.example.entities.Move;

public interface WinningStrategy {
    public Boolean checkWinner(Board board, Move move);
    public void handleUndo(Board board,Move move);
}
