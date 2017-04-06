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

    }

    /**
     * Test of tryPlayerMove method, of class Game.
     */
    @Test
    public void testTryPlayerMoveOutOfBounds() {
        this.instance = new Game("A game title", 700, 700);
        this.instance.loadTestLevel();
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
        System.out.println("");
    }

    @Test
    public void testTyPlayerMoveThroughWall() {
        this.instance = new Game("A game title", 700, 700);
        this.instance.loadTestLevel();
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
        System.out.println("");
    }

    @Test
    public void testPlayerMoveToOtherTile() {
        this.instance = new Game("A game title", 700, 700);
        this.instance.loadTestLevel();
        //Checks if the player, is still standing on the same location.
        System.out.println("testPlayerMoveToOtherTile");
        Player firstStatePlayer = this.instance.getPlayer1();
        System.out.println(firstStatePlayer.getLocationX() + " :X");
        System.out.println(firstStatePlayer.getLocationX() + " :Y");
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        Player secondPlayerState = this.instance.getPlayer1();
        System.out.println(secondPlayerState.getLocationX() + " :X");
        System.out.println(secondPlayerState.getLocationX() + " :Y");
        assertEquals("The player did walk out of x bounds", firstStatePlayer.getLocationX(), secondPlayerState.getLocationX());
        assertEquals("The player did walk out of y bounds", firstStatePlayer.getLocationY(), secondPlayerState.getLocationY());
        System.out.println("");
    }

    @Test
    public void walkThroughBarricadeWithoutKey() {
        this.instance = new Game("A game title", 700, 700);
        this.instance.loadTestLevel();
        //Checks if the player, is still standing on the same location.
        System.out.println("walkThroughBarricadeWithoutKey");
        Player firstStatePlayer = this.instance.getPlayer1();
        System.out.println(firstStatePlayer.getLocationX() + " :X");
        System.out.println(firstStatePlayer.getLocationX() + " :Y");
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        System.out.println(firstStatePlayer.toString());
        instance.tryPlayerMove(KeyEvent.VK_RIGHT);
        Player secondPlayerState = this.instance.getPlayer1();
        System.out.println(secondPlayerState.getLocationX() + " :X");
        System.out.println(secondPlayerState.getLocationX() + " :Y");
        assertEquals("The player did walk out of x bounds", firstStatePlayer.getLocationX(), secondPlayerState.getLocationX());
        assertEquals("The player did walk out of y bounds", firstStatePlayer.getLocationY() + 1, secondPlayerState.getLocationY());
        System.out.println("");
    }

    @Test
    public void testPickUpKey() {
        this.instance = new Game("A game title", 700, 700);
        this.instance.loadTestLevel();
        //Checks if the player, is still standing on the same location.
        System.out.println("testPickUpKey");
        Player firstStatePlayer = this.instance.getPlayer1();
        System.out.println(firstStatePlayer.getLocationX() + " :X");
        System.out.println(firstStatePlayer.getLocationX() + " :Y");
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_RIGHT);
        instance.tryPlayerMove(KeyEvent.VK_LEFT);
        Player secondPlayerState = this.instance.getPlayer1();
        System.out.println(secondPlayerState.getLocationX() + " :X");
        System.out.println(secondPlayerState.getLocationX() + " :Y");
        System.out.println(secondPlayerState.getKeyPinCode());
        assertEquals("Fail - The player didn't pickup the key", secondPlayerState.getKeyPinCode(), 43);
        System.out.println("");
    }

    @Test
    public void testOpenKeyWithBarricade() {
        this.instance = new Game("A game title", 700, 700);
        this.instance.loadTestLevel();
        //Checks if the player, is still standing on the same location.
        System.out.println("testOpenKeyWithBarricade");
        Player firstStatePlayer = this.instance.getPlayer1();
        System.out.println(firstStatePlayer.getLocationX() + " :X");
        System.out.println(firstStatePlayer.getLocationX() + " :Y");
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_RIGHT);
        instance.tryPlayerMove(KeyEvent.VK_LEFT);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_RIGHT);
        Player secondPlayerState = this.instance.getPlayer1();
        System.out.println(secondPlayerState.getLocationX() + " :X");
        System.out.println(secondPlayerState.getLocationX() + " :Y");
        System.out.println(secondPlayerState.getKeyPinCode());
        assertEquals("Fail - The player didn't pickup the key", secondPlayerState.getKeyPinCode(), 43);
        System.out.println("");
    }

    @Test
    public void testWalkOnEndTile() {
        this.instance = new Game("A game title", 700, 700);
        this.instance.loadTestLevel();
        //Checks if the player, is still standing on the same location.
        System.out.println("testWalkOnEndTile");
        Player firstStatePlayer = this.instance.getPlayer1();
        System.out.println(firstStatePlayer.getLocationX() + " :X");
        System.out.println(firstStatePlayer.getLocationX() + " :Y");
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_DOWN);
        instance.tryPlayerMove(KeyEvent.VK_RIGHT);
        Player secondPlayerState = this.instance.getPlayer1();
        System.out.println(secondPlayerState.getLocationX() + " :X");
        System.out.println(secondPlayerState.getLocationX() + " :Y");
        System.out.println("");
    }

}
