/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import chess.ChessBoard;
import chess.ChessColour;
import chess.Coordinate;
import chess.King;
import chess.Knight;
import chess.Pawn;
import chess.Piece;
import chess.Queen;
import chess.Rook;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author schr
 */
public class ChessBoardTest {
    
    private static ChessBoard testBoard;
    private static ChessBoard resetBoard;
    
    public ChessBoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        resetBoard = new ChessBoard();
        
        Coordinate coordinates[] = new Coordinate[18];
        Piece pieces[] = new Piece[18];
        
        coordinates[0] = new Coordinate("a1"); pieces[0] = new Rook(ChessColour.WHITE); // White
        coordinates[1] = new Coordinate("b1"); pieces[1] = new Knight(ChessColour.WHITE);
        coordinates[2] = new Coordinate("e1"); pieces[2] = new King(ChessColour.WHITE);
        coordinates[3] = new Coordinate("h1"); pieces[3] = new Rook(ChessColour.WHITE);
        coordinates[4] = new Coordinate("b2"); pieces[4] = new Pawn(ChessColour.WHITE);
        coordinates[5] = new Coordinate("b5"); pieces[5] = new Queen(ChessColour.WHITE);
        coordinates[6] = new Coordinate("a7"); pieces[6] = new Pawn(ChessColour.BLACK);
        coordinates[7] = new Coordinate("b7"); pieces[7] = new Pawn(ChessColour.BLACK);
        coordinates[8] = new Coordinate("c7"); pieces[8] = new Pawn(ChessColour.BLACK);
        coordinates[9] = new Coordinate("d7"); pieces[9] = new Pawn(ChessColour.BLACK);
        coordinates[10] = new Coordinate("e7"); pieces[10] = new Pawn(ChessColour.BLACK);
        coordinates[11] = new Coordinate("f7"); pieces[11] = new Pawn(ChessColour.BLACK);
        coordinates[12] = new Coordinate("g7"); pieces[12] = new Pawn(ChessColour.BLACK);
        coordinates[13] = new Coordinate("h7"); pieces[13] = new Pawn(ChessColour.BLACK);
        coordinates[14] = new Coordinate("a8"); pieces[14] = new Rook(ChessColour.BLACK);
        coordinates[15] = new Coordinate("b8"); pieces[15] = new Knight(ChessColour.BLACK);
        coordinates[16] = new Coordinate("e8"); pieces[16] = new King(ChessColour.BLACK);
        coordinates[17] = new Coordinate("h8"); pieces[17] = new Rook(ChessColour.BLACK); // Black
              
        testBoard = new ChessBoard(coordinates, pieces);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testReset() {
       // TBD?  
    }
    
    @Test
     public void testPawns() {
         
        assertEquals(true, resetBoard.move( new Coordinate("d2"), new Coordinate("d4"))); // White Pawn - Move 2 forward
        assertEquals(true, resetBoard.move( new Coordinate("e7"), new Coordinate("e5"))); // Black Pawn =- Move 2 forward
        assertEquals(true, resetBoard.move( new Coordinate("d4"), new Coordinate("e5"))); // White Pawn takes Black Pawn
        assertEquals(true, resetBoard.move( new Coordinate("a7"), new Coordinate("a6"))); // Any legal move for Black to prepare for next test
        
        assertEquals(true, resetBoard.move( new Coordinate("b2"), new Coordinate("b3"))); // White Pawn - Move 1 forward
        assertEquals(true, resetBoard.move( new Coordinate("g7"), new Coordinate("g6"))); // Black Pawn =- Move 1 forward
        // In Part 1: This next test should pass (allowing a pawn to move 2 forward on 2nd move)
        //  (So you need a Black move next)
        //assertEquals(true, resetBoard.move( new Coordinate("b3"), new Coordinate("b5"))); // White Pawn - Move 2 forward (!FirstMove)
        // In Part 2: This next test should fail (restricting a pawn to moving 2 forward only on 1st move)
        //  (So you need a White move next)
        assertEquals(false, resetBoard.move( new Coordinate("b3"), new Coordinate("b5"))); // White Pawn - Move 2 forward
        assertEquals(true, resetBoard.move( new Coordinate("e5"), new Coordinate("e6"))); // Any legal move for White to prepare next move
                
        assertEquals(false, resetBoard.move( new Coordinate("g6"), new Coordinate("g7"))); // Black Pawn - Move 1 backward (No)
        assertEquals(false, resetBoard.move( new Coordinate("g6"), new Coordinate("f6"))); // Black Pawn - Move 1 left (No)
        assertEquals(false, resetBoard.move( new Coordinate("g6"), new Coordinate("h6"))); // Black Pawn - Move 1 right (No)

     }
            
     @Test
     public void testKings() {
        assertEquals(false, resetBoard.move( new Coordinate("e1"), new Coordinate("e2"))); // White King - 1 forward, occupied (No)
            // Notice: Using resetBoard!
            
        assertEquals(true, testBoard.move( new Coordinate("e1"), new Coordinate ("f1"))); // White King - Right
        assertEquals(true, testBoard.move ( new Coordinate("e8"), new Coordinate ("d8"))); // Black King - Left
        assertEquals(true, testBoard.move( new Coordinate("f1"), new Coordinate ("e1"))); // White King - Left
        assertEquals(true, testBoard.move ( new Coordinate("d8"), new Coordinate ("e8"))); // Black King - Right
        assertEquals(true, testBoard.move( new Coordinate("e1"), new Coordinate ("e2"))); // White King - Forward
        assertEquals(false, testBoard.move ( new Coordinate("e8"), new Coordinate ("e7"))); // Black King - Forward, Occupied (No) 
     }
     
     @Test
     public void testKnights() {
         
        assertEquals(true, resetBoard.move( new Coordinate("b1"), new Coordinate ("c3"))); // White Knight - Hop forward, right
        assertEquals(true, resetBoard.move ( new Coordinate("g8"), new Coordinate ("h6"))); // Black Knight - Hop forward, right
        assertEquals(true, resetBoard.move( new Coordinate("c3"), new Coordinate ("b1"))); // White Knight - Hop backward, left
        assertEquals(true, resetBoard.move ( new Coordinate("h6"), new Coordinate ("g8"))); // Black Knight - Hop backward, left 
        
        assertEquals(true, resetBoard.move( new Coordinate("b1"), new Coordinate ("a3"))); // White Knight - Hop forward, left
        assertEquals(true, resetBoard.move ( new Coordinate("g8"), new Coordinate ("f6"))); // Black Knight - Hop forward, left  
        assertEquals(true, resetBoard.move( new Coordinate("a3"), new Coordinate ("b1"))); // White Knight - Hop backward, right
        assertEquals(true, resetBoard.move ( new Coordinate("f6"), new Coordinate ("g8"))); // Black Knight - Hop forward, right 
        
        assertEquals(false,resetBoard.move( new Coordinate("b1"), new Coordinate ("b3"))); // White Knight - Hop forward (No)
        assertEquals(false, resetBoard.move ( new Coordinate("b1"), new Coordinate ("d1"))); // White Knight - Hop left (No) 
        assertEquals(false, resetBoard.move ( new Coordinate("b1"), new Coordinate ("a2"))); // White Knight - Hop left-diagonal (No)   
        assertEquals(false, resetBoard.move ( new Coordinate("b1"), new Coordinate ("a2"))); // White Knight - Hop left-diagonal (No)   

     }
}
