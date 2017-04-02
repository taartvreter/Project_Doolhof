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
public class Key extends GameElement {

    private int pinCode = 0;
    private final BufferedImage image;

    public Key(int pinCode) {
        this.pinCode = pinCode;
        //init Image with keycode
        BufferedImage keyWithoutPinImg = Assets.key;
        keyWithoutPinImg.createGraphics().drawString(String.valueOf(this.pinCode), 3, 23);
        this.image = keyWithoutPinImg;
    }

    @Override
    public BufferedImage getImage() {
        return this.image;
    }

    public int getKeyPinCode() {
        return this.pinCode;
    }

    @Override
    public boolean canWalkThrough() {
        return true;
    }
}
