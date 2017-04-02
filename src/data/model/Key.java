/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import java.awt.image.BufferedImage;

/**
 *
 * @author hwpva
 */
public class Key extends GameElement {

    private int pinCode = 0;

    public Key() {
        java.util.Random rand = new java.util.Random(100);

    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

    public int getKeyPinCode() {
        return this.pinCode;
    }

    @Override
    public boolean canWalkThrough() {
        return true;
    }
}
