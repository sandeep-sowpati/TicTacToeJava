package org.example.designPatterns.strategies.botPlayingStrategy;

import org.example.entities.BotDifficultyLevel;

public class BotPlayingFactory {
    BotPlayingStrategy botPlayingStrategy;

    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel (BotDifficultyLevel botDifficultyLevel){
        return new EasyBotStrategy();
        /*
         return switch(botDifficultyLevel){
         case EASY -> new EasyBotStrategy();
         case MEDIUM -> new MediumBotStrategy();
         case HARD -> new HardBotStrategy();
         };
        */
    }
}
