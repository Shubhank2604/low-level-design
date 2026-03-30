package models;
public class PlayingPiece {
    
    PieceType pieceType;

    public PieceType getPieceType() {
        return pieceType;
    }

    PlayingPiece(PieceType pieceType) {
        this.pieceType = pieceType;
    }
}
