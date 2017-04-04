/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import gfx.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author hwpva
 */
public class Player {

    private Key keyPocket;
    private int locationX;
    private int locationY;

    public Player(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.keyPocket = null;
    }

    public Player(Key firstKey) {
        this.keyPocket = firstKey;
    }

    public BufferedImage getImage() {
        return Assets.player;
    }

    public void move() {
    }

    /**
     * Puts the current key of the player into the barricade and turns te key to
     * see if the barricade opens.
     */
    public void putKeyInBarricade() {
    }

    public void checkKeyPocket() {
    }

    public void pickUpKey() {
    }

    public int getKeyPinCode() {
        return this.keyPocket.getKeyPinCode();
    }

    /**
     * @return the locationX
     */
    public int getLocationX() {
        return locationX;
    }

    /**
     * @param locationX the locationX to set
     */
    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    /**
     * @return the locationY
     */
    public int getLocationY() {
        return locationY;
    }

    /**
     * @param locationY the locationY to set
     */
    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }
    
}
