package org.example.designPatterns.strategies.winningStrategy;

import org.example.entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    List<Map<Symbol,Integer>> rowWinner;

    public RowWinningStrategy(int size, List<Player> players){
        rowWinner = new ArrayList<>();

        for(int i=0;i<size;i++){
            Map<Symbol,Integer> dummy = new HashMap<>();
            for(Player player:players){
                dummy.put(player.getSymbol(),0);
            }
            rowWinner.add(dummy);
        }
    }
    @Override
    public Boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        rowWinner.get(row).put(symbol,rowWinner.get(row).get(symbol)+1);
        if(rowWinner.get(row).get(symbol)== board.getSize()) return true;
        return  false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        rowWinner.get(col).put(symbol,rowWinner.get(col).get(symbol)-1);
    }
}
