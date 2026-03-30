import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

import models.Board;
import models.*;

public class TicTacToe {

    Deque<Player> players;
    Board gameBoard;
    public Player winner;

    public TicTacToe() {
        // Initialize Board of size 3
        this.gameBoard = new Board(3);
    }
    public void initializeGame() {

        // Creating 2 Players
        players = new LinkedList<>();
        PieceX crossPiece = new PieceX();
        Player player1 = new Player("Shubhank", crossPiece);

        PieceO noughtsPiece = new PieceO();
        Player player2 = new Player("Nandini", noughtsPiece);

        players.add(player1);
        players.add(player2);
    }

    public String startGame() {

        boolean noWinner = true;
        Scanner inputScanner = new Scanner(System.in);
        while (noWinner) {

            // Remove the player whose turn is and also put the player in the list back
            Player currentPlayer = players.removeFirst();

            // Get the free space from the board
            gameBoard.printBoard();
            if (!gameBoard.hasFreeCells()) {
                noWinner = false;
                continue;
            }

            // Read the user input
            System.out.print("Player: " + currentPlayer.getName() + " - Please enter [row, column]: ");
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);


            // Place the piece in the board
            boolean validMove = gameBoard.addPiece(inputRow, inputColumn, currentPlayer.getPlayingPiece());
            if (!validMove) {
                // Invalid Move: Player can not insert the piece into this cell, player has to choose another cell
                System.out.println("Incorrect position chosen, try again!");
                players.addFirst(currentPlayer); // Add the player back to the queue(in the front)
                continue;
            }
            players.addLast(currentPlayer); // Add the player to the end of the queue

            // Check if the valid move is a winning move or not
            boolean isWinner = checkForWinner(inputRow, inputColumn, currentPlayer.getPlayingPiece().getPieceType());
            if (isWinner) {
                gameBoard.printBoard();
                winner = currentPlayer;
                return "WIN";
            }
        }

        return "DRAW";
    }

    public boolean checkForWinner(int row, int column, PieceType pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        // Check Row
        for (int i = 0; i < gameBoard.getSize(); i++) {
            PlayingPiece piece = gameBoard.getPiece(row, i);
            if (piece == null || piece.getPieceType() != pieceType) {
                rowMatch = false;
                break;
            }
        }

        // Check Column
        for (int i = 0; i < gameBoard.getSize(); i++) {
            PlayingPiece piece = gameBoard.getPiece(i, column);
            if (piece == null || piece.getPieceType() != pieceType) {
                columnMatch = false;
                break;
            }
        }

        // Check Diagonally
        for (int i = 0, j = 0; i < gameBoard.getSize(); i++, j++) {
            PlayingPiece piece = gameBoard.getPiece(i, j);
            if (piece == null || piece.getPieceType() != pieceType) {
                diagonalMatch = false;
                break;
            }
        }

        // Check Anti-Diagonally
        for (int i = 0, j = gameBoard.getSize() - 1; i < gameBoard.getSize(); i++, j--) {
            PlayingPiece piece = gameBoard.getPiece(i, j);
            if (piece == null || piece.getPieceType() != pieceType) {
                antiDiagonalMatch = false;
                break;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }

}
