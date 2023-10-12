package org.example.controllers;

import org.example.designPatterns.strategies.winningStrategy.WinningStrategy;
import org.example.entities.Game;
import org.example.entities.GameStatus;
import org.example.entities.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimension,
                           List<Player> players,
                           List<WinningStrategy> winningStrategies) throws Exception {
        return Game.getBuilder().setDimension(dimension).setPlayers(players).setWinningStrategies(winningStrategies).build();
    }
    public void displayBoard(Game game){
        game.printBoard();
    }

    public void undo(Game game){
        game.undo();
    }

    public void makeMove(Game game){
        game.makemove();
    }
    public void printResult(Game game){
        game.printResult();
    }

    public GameStatus gameStatus(Game game){
        return game.getGameStatus();
    }

}
