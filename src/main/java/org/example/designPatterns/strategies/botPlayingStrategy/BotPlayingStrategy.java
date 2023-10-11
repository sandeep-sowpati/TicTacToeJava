package org.example.designPatterns.strategies.botPlayingStrategy;

import org.example.entities.Board;
import org.example.entities.Cell;

public interface BotPlayingStrategy {
    Cell makemove(Board board);
}
