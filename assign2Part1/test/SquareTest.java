/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.Assert;
import org.junit.Test;

import chess.Coordinate;
import chess.Piece;
import chess.Square;

/**
 *
 * @author Cheryl
 */
public class SquareTest {
    
    public SquareTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
    public void testConstructorUnoccupied() {
         Square s = new Square ( new Coordinate(0,1) );
         Assert.assertEquals(0, s.getColumnNumber());
         Assert.assertEquals(1, s.getRowNumber());
         Assert.assertEquals('a', s.getColumn());
         Assert.assertEquals('2', s.getRow());
         Assert.assertEquals(s.getPiece(), null);
         Assert.assertFalse("Square should be unoccupied", s.isOccupied());
     }
    @Test
    public void testConstructorOccupied() {
         Piece p = new Piece('p');
         Square s = new Square ( new Coordinate(0,1), p );
         Assert.assertEquals(0, s.getColumnNumber());
         Assert.assertEquals(1, s.getRowNumber());
         Assert.assertEquals('a', s.getColumn());
         Assert.assertEquals('2', s.getRow());
         Assert.assertEquals(p, s.getPiece());
         Assert.assertTrue("Square should be occupied", s.isOccupied());
     }
          
    @Test
    public void testDeleteUnoccupied() {
        Square s = new Square ( new Coordinate(0,0) );
        Piece p = s.deletePiece();
        Assert.assertEquals(null, p);
        Assert.assertFalse( s.isOccupied() );
    }
    
    @Test
    public void testDeleteOccupied() {
        Piece p = new Piece('p');
        Square s = new Square ( new Coordinate(0,0), p );
        Piece deletedPiece = s.deletePiece();
        Assert.assertEquals(deletedPiece, p);
        Assert.assertFalse( s.isOccupied() );
    }
    
    @Test
    public void testAddUnoccupied() {
        Square s = new Square ( new Coordinate(0,0) );
        Piece p = new Piece('p');
        Piece previousPiece = s.addPiece(p);
        Assert.assertEquals(null, previousPiece);
        Assert.assertEquals(s.getPiece(), p);
        Assert.assertTrue( s.isOccupied() );
    }
    
    @Test
    public void testAddOccupied() {
        Piece p = new Piece('p');
        Square s = new Square ( new Coordinate(0,0), p );
        Piece secondPiece = new Piece('P');
        Piece previousPiece = s.addPiece(secondPiece);
        Assert.assertEquals(p, previousPiece);
        Assert.assertEquals(s.getPiece(), secondPiece);
        Assert.assertTrue( s.isOccupied() );
    }
    
    
    @Test
    public void testtoStringUnoccupied () {
        Square s = new Square ( new Coordinate(0,0) );
        String str = s.toString();
        Assert.assertEquals("Square(0,0): ", str);
    }
    
    @Test
    public void testtoStringOccupied () {
        Piece p = new Piece('p');
        Square s = new Square ( new Coordinate(1,0), p );
        String str = s.toString();
        Assert.assertEquals("Square(1,0):"+p.toString(), str);
    }
}
