/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import gfx.Asset;
import gfx.ImageLoader;
import java.awt.Color;
import java.awt.Graphics;
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
        this.image = this.createImage();
    }

    private BufferedImage createImage() {
        BufferedImage keyWithoutPin = Asset.key;
        ColorModel cm = keyWithoutPin.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = keyWithoutPin.copyData(null);
        BufferedImage keyWithPin = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        Graphics g = keyWithPin.createGraphics();
        g.setColor(Color.red);
        g.drawString(String.valueOf(this.pinCode), 3, 23);
        g.dispose();
        return keyWithPin;
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
