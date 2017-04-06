/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static com.oracle.nio.BufferSecrets.instance;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.CHAR_UNDEFINED;

import org.junit.Test;


/**
 *
 * @author hwpva
 */
public class GameTest {

    
    public GameTest() {

    }

    public void testTryPlayerMove1() {
        System.out.println("tryPlayerMove");
        KeyEvent e = new KeyEvent(null, 1, 0L, KeyEvent.VK_UP, CHAR_UNDEFINED);
        
        //instance.tryPlayerMove(e);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
