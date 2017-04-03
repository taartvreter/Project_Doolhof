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
public class Wall extends GameElement {

    private final BufferedImage image;

    public Wall() {
        this.image = Assets.wall;
    }

    @Override
    public boolean canWalkThrough() {
        return false;
    }

    /**
     * @return the image
     */
    @Override
    public BufferedImage getImage() {
        return image;
    }
}
