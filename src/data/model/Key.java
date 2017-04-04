/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author hwpva
 */
public class Key extends GameElement {

    private final int pinCode;
    private final BufferedImage image;

    public Key(int pinCode) {
        this.pinCode = pinCode;
        //init Image with keycode
        BufferedImage keyImage = Assets.key;
        Graphics2D keyDrawingCanvas = keyImage.createGraphics();
        keyDrawingCanvas.setColor(Color.red);
        keyDrawingCanvas.drawString(String.valueOf(this.pinCode), 3, 23);
        keyDrawingCanvas.dispose();
        this.image = keyImage;
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
