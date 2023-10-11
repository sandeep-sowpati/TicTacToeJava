package org.example.entities;

import org.example.designPatterns.strategies.botPlayingStrategy.BotPlayingStrategy;

public class BotPlayer extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private final BotPlayingStrategy botPlayingStrategy;
    public BotPlayer(String name, Character character, BotDifficultyLevel botDifficultyLevel, BotPlayingStrategy botPlayingStrategy){
        super(name,character,PlayerType.BOT_PLAYER);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = botPlayingStrategy;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Cell makemove(Board board) {
        return botPlayingStrategy.makemove(board);
    }
}
