package org.example.designPatterns.strategies.winningStrategy;

import org.example.entities.Board;
import org.example.entities.Move;
import org.example.entities.Player;
import org.example.entities.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{

    List<Map<Symbol,Integer>> colWinner;

    public ColumnWinningStrategy(int size, List<Player> players){
        colWinner = new ArrayList<>();

        for(int i=0;i<size;i++){
            Map<Symbol,Integer> dummy = new HashMap<>();
            for(Player player:players){
                dummy.put(player.getSymbol(),0);
            }
            colWinner.add(dummy);
        }
    }
    @Override
    public Boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        colWinner.get(col).put(symbol,colWinner.get(col).get(symbol)+1);
        if(colWinner.get(col).get(symbol)== board.getSize()) return true;
        return  false;
    }
    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        colWinner.get(col).put(symbol,colWinner.get(col).get(symbol)-1);
    }
}
