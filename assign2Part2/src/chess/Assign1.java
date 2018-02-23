/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;
import chess.Coordinate;
/**
 *
 * @author Cheryl
 */
public class Assign1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ChessBoard board = new ChessBoard();
        
     
        System.out.println("Board:" + board.toString());
        System.out.println("FEN:" + board.toFEN());
        
        board.move ( new Coordinate("b2"), new Coordinate("b3") );
        board.move ( new Coordinate("g8"), new Coordinate("f6") );
        System.out.println("FEN:" + board.toFEN());
           
        // Now construct a test board -- game already in play
        
        // Sample board = RN,,K,,R/,P,,,,,,/,,,,,,,,/,,,,,,,,/,Q,,,,,,/,,,,,,,,/pppppppp/rn,,k,,r/
        Coordinate coordinates[] = new Coordinate[18];
        Piece pieces[] = new Piece[18];
        
        coordinates[0] = new Coordinate(0,0); pieces[0] = new Rook(ChessColour.WHITE);
        coordinates[1] = new Coordinate(1,0); pieces[1] = new Knight(ChessColour.WHITE);
        coordinates[2] = new Coordinate(4,0); pieces[2] = new King(ChessColour.WHITE);
        coordinates[3] = new Coordinate(7,0); pieces[3] = new Rook(ChessColour.WHITE);
        coordinates[4] = new Coordinate(1,1); pieces[4] = new Pawn(ChessColour.WHITE);
        coordinates[5] = new Coordinate(1,4); pieces[5] = new Queen(ChessColour.WHITE);
        coordinates[6] = new Coordinate(0,6); pieces[6] = new Pawn(ChessColour.BLACK);
        coordinates[7] = new Coordinate(1,6); pieces[7] = new Pawn(ChessColour.BLACK);
        coordinates[8] = new Coordinate(2,6); pieces[8] = new Pawn(ChessColour.BLACK);
        coordinates[9] = new Coordinate(3,6); pieces[9] = new Pawn(ChessColour.BLACK);
        coordinates[10] = new Coordinate(4,6); pieces[10] = new Pawn(ChessColour.BLACK);
        coordinates[11] = new Coordinate(5,6); pieces[11] = new Pawn(ChessColour.BLACK);
        coordinates[12] = new Coordinate(6,6); pieces[12] = new Pawn(ChessColour.BLACK);
        coordinates[13] = new Coordinate(7,6); pieces[13] = new Pawn(ChessColour.BLACK);
        coordinates[14] = new Coordinate(0,7); pieces[14] = new Rook(ChessColour.BLACK);
        coordinates[15] = new Coordinate(1,7); pieces[15] = new Knight(ChessColour.BLACK);
        coordinates[16] = new Coordinate(4,7); pieces[16] = new King(ChessColour.BLACK);
        coordinates[17] = new Coordinate(7,7); pieces[17] = new Rook(ChessColour.BLACK);
              
        board = new ChessBoard(coordinates, pieces);
        System.out.println("FEN:" + board.toFEN());
             
    }
    
}
