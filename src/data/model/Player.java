/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import gfx.Asset;
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
        return Asset.player;
    }

    public void move(String move) {
        switch (move) {
            case "up":
                this.locationY -= 1;
                break;
            case "down":
                this.locationY += 1;
                break;
            case "left":
                this.locationX -= 1;
                break;
            case "right":
                this.locationX += 1;
                break;
        }
    }

    /**
     * Puts the current key of the player into the barricade and turns the key
     * to see if the barricade opens.
     *
     * @param openableBarricade
     * @return returns if the barricade disappears, when the player put the key
     * in the barricade.
     */
    public Barricade putKeyInBarricade(Barricade openableBarricade) {
        if (this.keyPocket != null) {
            if (openableBarricade.checkKey(keyPocket)) {
                System.out.println("The barricade magically opens!");

                return null;
            }
        }
        System.out.println("The barricade stays closed...");
        return openableBarricade;

    }

    public Key pickUpKey(Key pickedUpKey) {
        this.keyPocket = pickedUpKey;
        return null;
    }

    public int getKeyPinCode() {
        return this.getKeyPocket().getKeyPinCode();
    }

    /**
     * @return the locationX
     */
    public int getLocationX() {
        return locationX;
    }

    /**
     * @return the locationY
     */
    public int getLocationY() {
        return locationY;
    }

    /**
     * @return the keyPocket
     */
    public Key getKeyPocket() {
        return keyPocket;
    }

}
