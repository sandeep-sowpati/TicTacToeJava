package org.example.entities;

import org.example.designPatterns.strategies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private int currentPlayerIndex;
    private List<WinningStrategy> winningStrategies;
    private Player winner;
    private GameStatus gameStatus;

    public static Builder getBuilder(){
        return new Builder();
    }

    private Game(int dimension, List<Player> players,List<WinningStrategy> winningStrategies){
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.winningStrategies = winningStrategies;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void printResult(){
        if(gameStatus.equals(GameStatus.ENDED)){
            System.out.println("Game has ended");
            System.out.println("The winner is: "+winner);
        }
        else {
            System.out.println("Game is draw");
        }
    }

    public void printBoard(){
        board.print();
    }

    public void undo() {
        if (moves.isEmpty()) {
            System.out.println("No move. Can't undo.");
            return;
        }

        Move lastMove = moves.get(moves.size() - 1);

        for (WinningStrategy winningStrategy: winningStrategies) {
            winningStrategy.handleUndo(board, lastMove);
        }

        Cell cellInBoard = lastMove.getCell();
        cellInBoard.setCellStatus(CellStatus.EMPTY);
        cellInBoard.setPlayer(null);

        moves.remove(lastMove);

        currentPlayerIndex -= 1;
        currentPlayerIndex += players.size();
        currentPlayerIndex %= players.size();
    }

    public boolean validateMove(Cell cell){
        int row= cell.getRow();
        int col = cell.getCol();
        if(row<0 || col< 0|| row>= board.getSize() || col>= board.getSize()) return false;
        return board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.EMPTY);
    }

    public boolean checkGameWinner(Move move){
        for(WinningStrategy winningStrategy:winningStrategies){
            if(winningStrategy.checkWinner(board,move)) {
                winner = move.getPlayer();
                gameStatus = GameStatus.ENDED;
                return true;
            }
        }
        return false;
    }

    public boolean checkDraw(Move move){
        int dimension = board.getSize();
        if(moves.size()== dimension*dimension) {
            gameStatus = GameStatus.DRAW;
            return true;
        }
        return  false;
    }

    public void makemove(){
        Player player = players.get(currentPlayerIndex);
        System.out.println("This is "+ player.getSymbol() +"'s turn");
        Cell proposedCell = player.makemove(board);
        System.out.println("Move made at "+ proposedCell.getRow() +" row and "+ proposedCell.getCol()+ " col." );
        if(!validateMove(proposedCell)){
            System.out.println("Invalid Move please try again.");
            return;
        }
        Cell cellInBoard = board.getBoard().get(proposedCell.getRow()).get(proposedCell.getCol());
        cellInBoard.setCellStatus(CellStatus.FILLED);
        cellInBoard.setPlayer(player);

        Move move = new Move(cellInBoard,player);
        moves.add(move);

        if(checkGameWinner(move)) return;
        else if(checkDraw(move)) return;
        currentPlayerIndex += 1;
        currentPlayerIndex %= players.size();
    }

    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        private Builder(){
        }

        public Builder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies){
            this.winningStrategies = winningStrategies;
            return this;
        }
        public Builder setDimension(int dimension){
            this.dimension = dimension;
            return this;
        }

        private boolean valid(){
//          Single player can't possible
            if(this.players.size() < 2) return false;
//          Number of players is not same as dimension
            if(this.players.size() != this.dimension) return false;
//          Number of bots count
            int botcount=0;
            for(Player player:players){
                if(player.getPlayerType().equals(PlayerType.BOT_PLAYER)) botcount++;
            }
            if(botcount>=2) return false;
//          Each Player Different symbol
            Set<Character> characters = new HashSet<>();
            for(Player player:players){
                characters.add(player.getSymbol().getCharacter());
            }
            if(characters.size() != players.size()) return false;

            return true;
        }

        public Game build() throws Exception {
            if(!valid()){
                throw new Exception("Game class has some errors");
            }
            return new Game(dimension,players,winningStrategies);
        }
    }
}
