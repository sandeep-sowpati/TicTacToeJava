package org.example.entities;

import org.example.designPatterns.strategies.botPlayingStrategy.BotPlayingFactory;
import org.example.designPatterns.strategies.botPlayingStrategy.BotPlayingStrategy;

public class BotPlayer extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private final BotPlayingStrategy botPlayingStrategy;

    public BotPlayer(String name, Character c, PlayerType playerType,BotDifficultyLevel botDifficultyLevel) {
        super(name, c, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
        botPlayingStrategy = BotPlayingFactory.getBotPlayingStrategyForDifficultyLevel(botDifficultyLevel);
    }
    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Cell makeMove(Board board) {
        return botPlayingStrategy.makeMove(board);
    }
}
