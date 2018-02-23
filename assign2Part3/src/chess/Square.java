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
public class Square {
    
    private Coordinate coordinate;
    private Piece piece;
    
    public Square(Coordinate c) {
        this.coordinate = c;
        this.piece = null;      // No piece is positioned on this square
    }
    public Square(Coordinate c, Piece p) {
        this( c );
        this.piece = p;      // A piece is positioned on this square
    }
    
    public char getColumn() { return this.coordinate.getColumn(); }
    public char getRow() { return this.coordinate.getRow(); }
    
    public int getColumnNumber() { return this.coordinate.getColumnNumber(); }
    public int getRowNumber() { return this.coordinate.getRowNumber(); }
    
    public Coordinate getCoordinate() {
        return this.coordinate;
    }
    public Piece getPiece() {
        return this.piece;
    }
    
    public boolean isOccupied() {
        return (this.piece != null);
    }
    
    // Returns previous piece, if the square is currently occupied.
    public Piece addPiece(Piece newPiece) {
        Piece previous = this.piece;
        this.piece = newPiece;
        return previous;
    }
    
    public Piece deletePiece() {
        Piece previous = this.piece;
        this.piece = null;
        return previous;
    } 
    
    @Override
    public String toString() {
            String p = (this.piece == null) ? " " : piece.toString();
            return ("Square"+coordinate.toString()+":"+p);
    }
}
