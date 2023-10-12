package org.example.designPatterns.strategies.winningStrategy;

import org.example.entities.Board;
import org.example.entities.Move;
import org.example.entities.Player;
import org.example.entities.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DiagonalWinningStrategy implements WinningStrategy{
    private final Map<Symbol,Integer> leftDiagonal;
    private final Map<Symbol,Integer> rightDiagonal;

    public DiagonalWinningStrategy(List<Player> players){
        leftDiagonal = new HashMap<>();
        rightDiagonal = new HashMap<>();
        for(Player player:players){
            leftDiagonal.put(player.getSymbol(),0);
            rightDiagonal.put(player.getSymbol(),0);
        }
    }
    @Override
    public Boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(row==col){
            leftDiagonal.put(symbol, leftDiagonal.get(symbol)+1);
        } else if (row+col == board.getSize()-1) {
            rightDiagonal.put(symbol,rightDiagonal.get(symbol)+1);
        }
        return leftDiagonal.get(symbol) == board.getSize() || rightDiagonal.get(symbol) == board.getSize();
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(row==col){
            leftDiagonal.put(symbol, leftDiagonal.get(symbol)-1);
        } else if (row+col == board.getSize()-1) {
            rightDiagonal.put(symbol,rightDiagonal.get(symbol)-1);
        }
    }
}
