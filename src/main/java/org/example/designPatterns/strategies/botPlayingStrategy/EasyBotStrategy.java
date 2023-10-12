package org.example.designPatterns.strategies.botPlayingStrategy;

import org.example.entities.Board;
import org.example.entities.Cell;
import org.example.entities.CellStatus;

import java.util.List;

public class EasyBotStrategy implements BotPlayingStrategy{
    @Override
    public Cell makeMove(Board board) {
        // return the first empty cell.
        for(List<Cell> row: board.getBoard()) {
            for (Cell cell: row) {
                if (cell.getCellStatus().equals(CellStatus.EMPTY)) {
                    return cell;
                }
            }
        }

        return null;
    }
}
