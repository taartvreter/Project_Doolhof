/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import data.model.Player;
import data.model.Tile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hwpva
 */
public class LevelGetterTest {
    
    public LevelGetterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadMapToArray method, of class LevelGetter.
     */
    @Test
    public void testLoadMapToArray() {
        System.out.println("loadMapToArray");
        LevelGetter instance = new LevelGetter();
        Tile[][] expResult = null;
        Tile[][] result = instance.loadMapToArray(1);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadPlayer method, of class LevelGetter.
     */
    @Test
    public void testLoadPlayer() {
        System.out.println("loadPlayer");
        LevelGetter instance = new LevelGetter();
        Player expResult = null;
        Player result = instance.loadPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
