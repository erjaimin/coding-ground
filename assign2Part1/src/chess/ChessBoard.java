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
public class ChessBoard {
    
    private Square board[][];
    private ChessColour activeColour;
    private int fullMove;
    
    public ChessBoard() {
        board = new Square[8][8];
        for (int c=0; c<8; c++)
            for (int r=0; r<8; r++)
                board[c][r] = new Square( new Coordinate(c,r));
        reset();
        activeColour = ChessColour.WHITE;
        fullMove = 1;
    }
    
      /*
     *
    */
    public ChessBoard( Coordinate positions[], Piece pieces[])throws IllegalArgumentException
    {   
        if (positions.length != pieces.length) 
            throw new IllegalArgumentException ("The list of positions must correspond to the list of pieces");
        
        board = new Square[8][8];
        for (int r=0; r<8; r++)
            for (int c=0; c<8; c++)
                board[r][c] = new Square( new Coordinate(r,c));
        for (int i=0; i<positions.length;i++) {
            board[positions[i].getColumnNumber()][positions[i].getRowNumber()].addPiece(pieces[i]);
        }
        activeColour = ChessColour.WHITE;
        fullMove = 1;
    }
    private void reset()
    {
        // White rows
        board[0][0].addPiece (new Piece(ChessColour.WHITE, ChessPieces.ROOK));
        board[7][0].addPiece (new Piece(ChessColour.WHITE, ChessPieces.ROOK));  
        board[1][0].addPiece (new Piece(ChessColour.WHITE, ChessPieces.KNIGHT));
        board[6][0].addPiece (new Piece(ChessColour.WHITE, ChessPieces.KNIGHT));   
        board[2][0].addPiece (new Piece(ChessColour.WHITE, ChessPieces.BISHOP));
        board[5][0].addPiece (new Piece(ChessColour.WHITE, ChessPieces.BISHOP));
        board[3][0].addPiece (new Piece(ChessColour.WHITE, ChessPieces.QUEEN));
        board[4][0].addPiece (new Piece(ChessColour.WHITE, ChessPieces.KING));    
        
        for (int c=0; c<8; c++)
            board[c][1].addPiece( new Piece(ChessColour.WHITE, ChessPieces.PAWN));
       
        // Black rows
        board[0][7].addPiece (new Piece(ChessColour.BLACK, ChessPieces.ROOK));
        board[7][7].addPiece (new Piece(ChessColour.BLACK, ChessPieces.ROOK));  
        board[1][7].addPiece (new Piece(ChessColour.BLACK, ChessPieces.KNIGHT));
        board[6][7].addPiece (new Piece(ChessColour.BLACK, ChessPieces.KNIGHT));   
        board[2][7].addPiece (new Piece(ChessColour.BLACK, ChessPieces.BISHOP));
        board[5][7].addPiece (new Piece(ChessColour.BLACK, ChessPieces.BISHOP));
        board[3][7].addPiece (new Piece(ChessColour.BLACK, ChessPieces.QUEEN));
        board[4][7].addPiece (new Piece(ChessColour.BLACK, ChessPieces.KING)); 
        
        for (int c=0; c<8; c++)
            board[c][6].addPiece( new Piece(ChessColour.BLACK, ChessPieces.PAWN));
        
        // Middle rows : Make sure they are UNOCCUPIED by deleting
        Piece p;  
        for (int c=0; c<8; c++)
            for (int r=2; r<6; r++)
                p = board[c][r].deletePiece();
    
        
    }
    
    protected Square getSquare (Coordinate c)
    {
        return board[c.getColumnNumber()][c.getRowNumber()];
    }

	public boolean move(Coordinate src, Coordinate dest) {

		Square srcSquare = this.getSquare(src);
		if (!srcSquare.isOccupied())
			return false;
		Piece p = srcSquare.getPiece();
		if (!p.getColour().equals(activeColour))
			return false;
		if (p.isLegalMove(this, src, dest)) {
			Square destSquare = this.getSquare(dest);
			Piece takenPiece = destSquare.addPiece(p);
			srcSquare.deletePiece();
			activeColour = (activeColour == ChessColour.BLACK)
					? ChessColour.WHITE
					: ChessColour.BLACK;
			if (activeColour == ChessColour.WHITE)
				fullMove++;
			// fullMove is incremented only *AFTER* BLACK has moved. 
			return true;
		} else {
			return false;
		}	
	}
    
    public String toString() {
        
       String s = "Board\n";
       for (int r=7; r>=0; r--)
       {
           for (int c=0; c<8; c++)
           {
               Piece p = board[c][r].getPiece();
               s += (p == null) ? " " : p.getShortName();
               s += "_";
           }
           s += "\n";
       }
       return s;
    }
    
       public String toFEN() {
        
       String s = "";
       for (int r=7; r>=0; r--)
       {
           for (int c=0; c<8; c++)
           {
               Piece p = board[c][r].getPiece();
               s += (p == null) ? " " : p.getShortName();
           }
           s += "/";
       }
       s += " " + ((activeColour == ChessColour.WHITE) ? "w" : "b");
       s += " " + fullMove;
       s += "\n";
       
       return s;
    }
    
    
}
