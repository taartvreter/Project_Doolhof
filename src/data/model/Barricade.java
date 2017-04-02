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
public class Barricade extends GameElement {

    private final BufferedImage image;

    private final int pinCode;

    public Barricade(int pinCode) {
        this.pinCode = pinCode;
        
        //init Image with keycode
        BufferedImage barricadeWithoutPinImg = Assets.barricade;
        barricadeWithoutPinImg.createGraphics().drawString(String.valueOf(this.pinCode), 3, 23);
        this.image = barricadeWithoutPinImg;

    }

    @Override
    public BufferedImage getImage() {
        return this.image;
    }

    @Override
    public boolean canWalkThrough() {
        return false;
    }

    public int getPinCode() {
        return this.pinCode;
    }
}
