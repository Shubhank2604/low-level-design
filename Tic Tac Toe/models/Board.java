package models;

public class Board {
    
    private final int size;
    private final PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public int getSize() {
        return size;
    }

    public PlayingPiece getPiece(int row, int col) {
        return board[row][col];
    }

    public boolean hasFreeCells() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean addPiece(int row, int col, PlayingPiece playingPiece) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        if(board[row][col] != null) {
            return false;
        }
        board[row][col] = playingPiece;
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getPieceType().name() + "   ");
                } else {
                    System.out.print("    ");
                }
                System.out.print(" | ");
            }
            System.out.println();
            System.out.println("--------------------");
            System.out.println();
        }
    }

}
