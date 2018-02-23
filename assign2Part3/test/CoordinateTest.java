/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import chess.Coordinate;

/**
 *
 * @author Cheryl
 */
public class CoordinateTest {
    
    public CoordinateTest() {
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testConstructorGood() {
         Coordinate c = new Coordinate(7,6);
         Assert.assertEquals(6, c.getRowNumber());
         Assert.assertEquals(7, c.getColumnNumber());
     }
    
    @Test
     public void testConstructorColumnTooHigh() {
         try {
            Coordinate c = new Coordinate(8,0);
            fail();
         } catch (IndexOutOfBoundsException e) { /* Expected */ }
             
     }
    
    @Test
    public void testConstructorRowTooHigh() {
        
         try {
            Coordinate c = new Coordinate(0,8);
            fail();
         } catch (IndexOutOfBoundsException e) { /* Expected */ }
     }
    
         @Test
    public void testCharConstructorGood() {
         Coordinate c = new Coordinate('h','7');
         Assert.assertEquals('7', c.getRow());
         Assert.assertEquals('h', c.getColumn());
         Assert.assertEquals(6, c.getRowNumber());
         Assert.assertEquals(7, c.getColumnNumber());
     }
    
    @Test
     public void testCharConstructorColumnTooHigh() {
         try {
            Coordinate c = new Coordinate('i','1');
            fail();
         } catch (IndexOutOfBoundsException e) { /* Expected */ }
             
     }
    
    @Test
    public void testCharConstructorRowTooLow() {
        
         try {
            Coordinate c = new Coordinate('a','0');
            fail();
         } catch (IndexOutOfBoundsException e) { /* Expected */ }
     }
    
    @Test
    public void testStringConstructorGood() {
         Coordinate c = new Coordinate("a1");
         Assert.assertEquals('a', c.getColumn());
         Assert.assertEquals('1', c.getRow());
         Assert.assertEquals(0, c.getColumnNumber());
         Assert.assertEquals(0, c.getRowNumber());
     }
    @Test
    public void testStringConstructorTooLong() {
        
         try {
            Coordinate c = new Coordinate("a0 ");
            fail();
         } catch (IllegalArgumentException e) { /* Expected */ }
     }
        @Test
    public void testStringConstructorTooShort() {
        
         try {
            Coordinate c = new Coordinate("a");
            fail();
         } catch (IllegalArgumentException e) { /* Expected */ }
     }
      
    @Test
    public void testRowColumnLow () {
        Coordinate c = new Coordinate(0,0);
        Assert.assertEquals('a', c.getColumn());
        Assert.assertEquals('1', c.getRow());
    }
    @Test
    public void testRowColumnHigh () {
        Coordinate c = new Coordinate(7,7);
        Assert.assertEquals('h', c.getColumn());
        Assert.assertEquals('8', c.getRow());
    }
    
    @Test
    public void testName () {
        Coordinate c = new Coordinate(0,0);
        String s = c.name();
        Assert.assertEquals("a1", s);
    }
    
    @Test
    public void testtoString () {
        Coordinate c = new Coordinate(0,0);
        String s = c.toString();
        Assert.assertEquals("(0,0)", s);
    }
}
