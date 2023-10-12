package org.example;

import org.example.controllers.GameController;
import org.example.designPatterns.strategies.botPlayingStrategy.EasyBotStrategy;
import org.example.designPatterns.strategies.winningStrategy.ColumnWinningStrategy;
import org.example.designPatterns.strategies.winningStrategy.DiagonalWinningStrategy;
import org.example.designPatterns.strategies.winningStrategy.RowWinningStrategy;
import org.example.entities.Game;
import org.example.entities.GameStatus;
import org.example.entities.Player;
import org.example.entities.PlayerType;

import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        Game game;
        List<Player> players = List.of(
                new Player("Sandeep",'x', PlayerType.HUMAN_PLAYER),
                new Player("Aman", '0',PlayerType.BOT_PLAYER)
        );

        int dimension =3;
        try {
            game = gameController.createGame(
                    dimension,
                    players,
                    List.of(
                            new RowWinningStrategy(dimension,players),
                            new ColumnWinningStrategy(dimension,players),
                            new DiagonalWinningStrategy(players)
                    )
            );
        } catch (Exception e) {
            System.out.println("invalid Args enterd please enter again");
            return;
        }

        System.out.println("---------------Game is starting----------------");
        while(gameController.gameStatus(game).equals(GameStatus.IN_PROGRESS)){
            System.out.println(".......Current board............");
            gameController.displayBoard(game);
            System.out.println("undo (y/n)?");
            String str = scanner.next();
            if(str.equals("y")) gameController.undo(game);
            gameController.makeMove(game);
        }

        gameController.printResult(game);
        gameController.displayBoard(game);
    }
}