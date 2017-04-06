/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.model.Player;
import java.awt.event.KeyEvent;
import java.util.Date;
/*
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;*/
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hwpva
 */
public class GameTest {

    private Game instance;

    public GameTest() {
        this.instance = new Game("12123", 700, 700);
    }

    /**
     * Test of tryPlayerMove method, of class Game.
     */
    @Test
    public void testTryPlayerMoveOutOfBounds() {
        //Checks if the player, is still standing on the same location after trying out of the map.
        System.out.println("tryPlayerMove");
        Player firstStatePlayer = this.instance.getPlayer1();
        System.out.println(firstStatePlayer.getLocationX() + " :X");
        System.out.println(firstStatePlayer.getLocationX() + " :Y");
        Date sd = new Date();
        KeyEvent e = new KeyEvent(instance, KeyEvent.KEY_PRESSED, sd.getTime(), KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        instance.tryPlayerMove(e);
        Player secondPlayerState = this.instance.getPlayer1();
        assertEquals("The player did walk out of x bounds", firstStatePlayer.getLocationX(), secondPlayerState.getLocationX());
        assertEquals("The player did walk out of y bounds", firstStatePlayer.getLocationY(), secondPlayerState.getLocationY());
    }

    @Test
    public void testTryPlayerMoveAgainstWall() {
        //Checks if the player, is still standing on the same location.
        System.out.println("tryPlayerMove");
        Player firstStatePlayer = this.instance.getPlayer1();
        System.out.println(firstStatePlayer.getLocationX() + " :X");
        System.out.println(firstStatePlayer.getLocationX() + " :Y");
        KeyEvent e = new KeyEvent(instance, KeyEvent.KEY_PRESSED, new Date().getTime(), KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        instance.tryPlayerMove(e);
        e = new KeyEvent(instance, KeyEvent.KEY_PRESSED, new Date().getTime(), KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        
        instance.tryPlayerMove(e);
        Player secondPlayerState = this.instance.getPlayer1();
        assertEquals("The player did walk out of x bounds", firstStatePlayer.getLocationX(), secondPlayerState.getLocationX());
        assertEquals("The player did walk out of y bounds", firstStatePlayer.getLocationY(), secondPlayerState.getLocationY());

        //fail("The test case is a prototype.");
    }

}
