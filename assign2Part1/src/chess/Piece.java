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
    
           
    public Piece() {}
    
    public Piece(ChessColour colour, ChessPieces name)
    {
        this.colour = colour;
        this.name = name;
        this.shortName = name.getShortName();
        if (colour == ChessColour.BLACK) this.shortName = Character.toLowerCase(this.shortName);      
    }
    
       
    public Piece(char shortName)
    {
        this.shortName = shortName;  
        char temp = Character.toUpperCase(shortName);
        switch (temp) {            
            case 'P' : this.name = ChessPieces.PAWN; break;
            case 'N' : this.name = ChessPieces.KNIGHT; break; 
            case 'B' : this.name = ChessPieces.BISHOP; break;
            case 'R' : this.name = ChessPieces.ROOK; break;
            case 'Q' : this.name = ChessPieces.QUEEN; break;
            case 'K' : this.name = ChessPieces.KING; break;
            default: throw new IllegalArgumentException ("shortname " + shortName + " is not valid");
        }
        this.colour = (Character.isLowerCase(shortName)) ? ChessColour.BLACK : ChessColour.WHITE;

    }
    
    public ChessColour getColour() { return this.colour; }
    public ChessPieces getName() { return this.name; }
    public char getShortName() { return this.shortName; }
    
    public String toString() { return colour + " " + name; }

	public boolean isLegalMove(ChessBoard chessBoard, Coordinate src,
			Coordinate dest) {
		boolean isLegal = false;
		if(!checkIfAlreadyOccupied(chessBoard, dest)) {
			int srcRow = src.getRowNumber();
			int rowDiff = dest.getRowNumber() - srcRow;
			int columnDiff = dest.getColumnNumber() - src.getColumnNumber();
			switch (name) {
				case KING:
					if(checkDiff(rowDiff, 1) || checkDiff(columnDiff, 1))
						isLegal = true;
					break;
				case KNIGHT:
					if((checkDiff(rowDiff, 1) && checkDiff(columnDiff, 2)) || 
							(checkDiff(rowDiff, 2) && checkDiff(columnDiff, 1)))
						isLegal = true;
					break;
				case PAWN:
					ChessColour destColour = null;
					Square destSquare = chessBoard.getSquare(dest);
					if(destSquare != null && destSquare.getPiece() != null) {
						destColour = destSquare.getPiece().getColour();
					}
					if(ChessColour.WHITE.equals(getColour())) {
						if(columnDiff == 1 && destColour != null && ChessColour.BLACK.equals(destColour)) {
							isLegal = true;
						}else if(checkDiffForward(rowDiff, 1) || checkDiffForward(rowDiff, 2)) {
							isLegal = true;
						}
					}else {
						if(columnDiff == 1 && destColour != null && ChessColour.WHITE.equals(destColour)) {
							isLegal = true;
						}else if(checkDiffForward(rowDiff, -1) || checkDiffForward(rowDiff, -2)) {
							isLegal = true;
						}
					}
					break;
				default:
					System.out.println("Piece not supported yet!");
					break;
			}
		}
		return isLegal;
	}

	private boolean checkIfAlreadyOccupied(ChessBoard chessBoard,
			Coordinate dest) {
		boolean isOccupied = false;
		Square square = chessBoard.getSquare(dest);
		if(square != null && square.getPiece() != null && square.getPiece().getColour().equals(this.colour)) {
			isOccupied = true;
		}
		return isOccupied;
	}

	private boolean checkDiff(int diff, int num) {
		return (diff == num || diff == -1 * num);
	}
	
	private boolean checkDiffForward(int diff, int num) {
		return diff == num;
	}
    
}
