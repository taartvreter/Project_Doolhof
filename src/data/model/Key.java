/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import gfx.Asset;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 *
 * @author hwpva
 */
public class Key extends GameElement {

    private int pinCode;
    private final BufferedImage image;

    public Key(int pinCode) {
        this.pinCode = pinCode;
        //init Image with keycode
        BufferedImage keyImage = Asset.key;
        Graphics2D keyDrawingCanvas = keyImage.createGraphics();
        keyDrawingCanvas.setColor(Color.red);
        System.out.println("String drawn on image " + String.valueOf(this.pinCode));
        keyDrawingCanvas.drawString(String.valueOf(this.pinCode), 3, 23);

        this.image = this.createImage();
    }

    private BufferedImage createImage() {
        BufferedImage keyImage = Asset.key;
        
        return null;

        // barricadeWithPinImg.createGraphics().drawString(String.valueOf(this.pinCode), 3, 23);
        //return barricadeWithPinImg;
        /*
        BufferedImage keyWithoutPin = Asset.key;
        ColorModel cm = keyWithoutPin.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = keyWithoutPin.copyData(null);
        BufferedImage keyWithPin = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        /*
        
        BufferedImage barricadeWithPinImg = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        barricadeWithPinImg.createGraphics().drawString(String.valueOf(this.pinCode), 3, 23);
        return keyWithPin;*/
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
