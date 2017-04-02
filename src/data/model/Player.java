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

    public Player() {
    }

    public BufferedImage getImage() {
        return Assets.player;
    }

    public void move() {
    }

    public void openBarricade() {
    }

    public void checkKeyPocket() {
    }

    public void pickUpKey() {
    }

    public int getKeyPinCode() {
        return this.keyPocket.getKeyPinCode();
    }
}
