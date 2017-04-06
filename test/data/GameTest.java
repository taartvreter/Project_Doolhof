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

    private final Game instance;

    public GameTest() {
        this.instance = new Game("A game title", 700, 700);
        this.instance.loadTestLevel();
    }

    /**
     * Test of tryPlayerMove method, of class Game.
     */
    @Test
    public void testTryPlayerMoveOutOfBounds() {
        //Checks if the player, is still standing on the same location after trying out of the map.
        System.out.println("testTryPlayerMoveOutOfBounds");
        Player firstStatePlayer = this.instance.getPlayer1();
        System.out.println(firstStatePlayer.getLocationX() + " :X");
        System.out.println(firstStatePlayer.getLocationX() + " :Y");
        instance.tryPlayerMove(KeyEvent.VK_UP);
        Player secondPlayerState = this.instance.getPlayer1();
        System.out.println(secondPlayerState.getLocationX() + " :X");
        System.out.println(secondPlayerState.getLocationX() + " :Y");
        assertEquals("The player did walk out of x bounds", firstStatePlayer.getLocationX(), secondPlayerState.getLocationX());
        assertEquals("The player did walk out of y bounds", firstStatePlayer.getLocationY(), secondPlayerState.getLocationY());
    }

    @Test
    public void testTyPlayerMoveThroughWall() {
        //Checks if the player, is still standing on the same location.
        System.out.println("testTyPlayerMoveThroughWall");
        Player firstStatePlayer = this.instance.getPlayer1();
        System.out.println(firstStatePlayer.getLocationX() + " :X");
        System.out.println(firstStatePlayer.getLocationX() + " :Y");
        instance.tryPlayerMove(KeyEvent.VK_RIGHT);
        Player secondPlayerState = this.instance.getPlayer1();
        System.out.println(firstStatePlayer.getLocationX() + " :X");
        System.out.println(firstStatePlayer.getLocationX() + " :Y");
        assertEquals("The player did walk out of x bounds", firstStatePlayer.getLocationX(), secondPlayerState.getLocationX());
        assertEquals("The player did walk out of y bounds", firstStatePlayer.getLocationY(), secondPlayerState.getLocationY());

    }

    @Test
    public void testPlayerMoveToOtherTile() {
        //Checks if the player, is still standing on the same location.
        System.out.println("testPlayerMoveToOtherTile");
        Player firstStatePlayer = this.instance.getPlayer1();
        System.out.println(firstStatePlayer.getLocationX() + " :X");
        System.out.println(firstStatePlayer.getLocationX() + " :Y");
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        Player secondPlayerState = this.instance.getPlayer1();
        System.out.println(secondPlayerState.getLocationX() + " :X");
        System.out.println(secondPlayerState.getLocationX() + " :Y");
        assertEquals("The player did walk out of x bounds", firstStatePlayer.getLocationX(), secondPlayerState.getLocationX());
        assertEquals("The player did walk out of y bounds", firstStatePlayer.getLocationY() + 1, secondPlayerState.getLocationY());
    }
}
