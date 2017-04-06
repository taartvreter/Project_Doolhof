/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.CHAR_UNDEFINED;
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
public class GameTest {

    private Game instance = new Game("12123", 700, 700);

    public GameTest() {

    }

    /**
     * Test of tryPlayerMove method, of class Game.
     */
    @Test
    public void testTryPlayerMove1() {
        System.out.println("tryPlayerMove");
        KeyEvent e = new KeyEvent(null, 1, 0L, KeyEvent.VK_UP, CHAR_UNDEFINED);
        
        instance.tryPlayerMove(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
