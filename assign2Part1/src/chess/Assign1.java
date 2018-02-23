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
        
        coordinates[0] = new Coordinate(0,0); pieces[0] = new Piece('R');
        coordinates[1] = new Coordinate(1,0); pieces[1] = new Piece('N');
        coordinates[2] = new Coordinate(4,0); pieces[2] = new Piece('K');
        coordinates[3] = new Coordinate(7,0); pieces[3] = new Piece('R');
        coordinates[4] = new Coordinate(1,1); pieces[4] = new Piece('P');
        coordinates[5] = new Coordinate(1,4); pieces[5] = new Piece('Q');
        coordinates[6] = new Coordinate(0,6); pieces[6] = new Piece('p');
        coordinates[7] = new Coordinate(1,6); pieces[7] = new Piece('p');
        coordinates[8] = new Coordinate(2,6); pieces[8] = new Piece('p');
        coordinates[9] = new Coordinate(3,6); pieces[9] = new Piece('p');
        coordinates[10] = new Coordinate(4,6); pieces[10] = new Piece('p');
        coordinates[11] = new Coordinate(5,6); pieces[11] = new Piece('p');
        coordinates[12] = new Coordinate(6,6); pieces[12] = new Piece('p');
        coordinates[13] = new Coordinate(7,6); pieces[13] = new Piece('p');
        coordinates[14] = new Coordinate(0,7); pieces[14] = new Piece('r');
        coordinates[15] = new Coordinate(1,7); pieces[15] = new Piece('n');
        coordinates[16] = new Coordinate(4,7); pieces[16] = new Piece('k');
        coordinates[17] = new Coordinate(7,7); pieces[17] = new Piece('r');
              
        board = new ChessBoard(coordinates, pieces);
        System.out.println("FEN:" + board.toFEN());
             
    }
    
}
