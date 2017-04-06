/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import gfx.Asset;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 *
 * @author hwpva
 */
public class Barricade extends GameElement {

    private final BufferedImage image;

    private final int pinCode;

    public Barricade(int pinCode) {
        this.pinCode = pinCode;
        this.image = this.createImage();

    }

    private BufferedImage createImage() {
        BufferedImage barricadeWithoutPinImg = Asset.barricade;
        ColorModel cm = barricadeWithoutPinImg.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = barricadeWithoutPinImg.copyData(null);
        BufferedImage barricadeWithPinImg = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        barricadeWithPinImg.createGraphics().drawString(String.valueOf(this.pinCode), 3, 23);
        return barricadeWithPinImg;
    }

    public boolean checkKey(Key key) {
        return (key.getKeyPinCode() == this.pinCode);
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
