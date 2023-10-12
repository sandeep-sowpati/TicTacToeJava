package org.example.entities;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String name;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(String name, Character c, PlayerType playerType){
        this.name = name;
        this.symbol = new Symbol(c);
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Cell makeMove(Board board){
        System.out.println("Please tell me the row you want to make the move: ");
        int row = scanner.nextInt();
        System.out.println("Please let me know the col you want to make the move: ");
        int col = scanner.nextInt();

        return new Cell(row,col);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
