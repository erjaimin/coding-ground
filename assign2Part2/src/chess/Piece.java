/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author Cheryl
 */
public class Piece {
    
    private ChessColour colour;
    private ChessPieces name;
    private char shortName;
    
    public Piece(ChessColour colour, ChessPieces name)
    {
        this.colour = colour;
        this.name = name;
        this.shortName = name.getShortName();
        if (colour == ChessColour.BLACK) this.shortName = Character.toLowerCase(this.shortName);      
    }
    
    public ChessColour getColour() { return this.colour; }
    public ChessPieces getName() { return this.name; }
    public char getShortName() { return this.shortName; }
    
    public String toString() { return colour + " " + name; }

	public boolean isLegalMove(ChessBoard chessBoard, Coordinate src,
			Coordinate dest) {
		boolean isOccupied = false;
		Square square = chessBoard.getSquare(dest);
		if(square != null && square.getPiece() != null && square.getPiece().getColour().equals(this.colour)) {
			isOccupied = true;
		}
		return isOccupied;
	}

	protected boolean checkDiff(int diff, int num) {
		return (diff == num || diff == -1 * num);
	}
	
	protected boolean checkDiffForward(int diff, int num) {
		return diff == num;
	}
    
}
